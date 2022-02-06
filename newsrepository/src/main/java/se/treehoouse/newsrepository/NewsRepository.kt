package se.treehoouse.newsrepository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import se.treehoouse.newsapi.model.Message
import se.treehoouse.newsapi.NewsApiService
import se.treehoouse.newsrepository.converters.*
import se.treehoouse.newsrepository.model.ErrorResult
import se.treehoouse.newsrepository.model.NewsArticle
import se.treehoouse.newsrepository.model.Result
import se.treehoouse.newsstorage.NewsDatabase
import java.lang.Exception

class NewsRepository(
    private val api: NewsApiService,
    private val db: NewsDatabase
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadTopics(topics: List<String>): Flow<Result<List<NewsArticle>>> {
        val filteredTopics = topics.filter { it.isNotBlank() }

        return combine(
            filteredTopics.map { loadRemoteTopic(it) }
        ) { results ->
            val allResults = results.combine()
            if(allResults is Result.Data){
                // TODO Save result to database async. Requires update loadArticle to be able to handle not saved articles
                db.newsDao().updateAll(
                    allResults.value.toArticlesDb()
                )
            }
            allResults
        }.flatMapLatest { result ->
            when(result) {
                is Result.Data -> flowOf(result)
                is Result.Error -> flowOf(result)
                is Result.NoNetworkError -> db.newsDao().all.map { it.toArticles().toSuccess() }
                is Result.ParsingError -> flowOf(result)
            }
        }
    }

    private fun loadRemoteTopic(topic: String): Flow<Result<List<NewsArticle>>> {
        return flow {
            try {
                val message: Message.ArticleMessageTO = api.everything(query = topic)

                val data: Result.Data<List<NewsArticle>> = message.articles
                    .toArticleModels()
                    .toSuccess()

                emit(data)
            } catch (e: Exception){
                emit(e.toFailure())
            }
        }
    }

    private fun Array<Result<List<NewsArticle>>>.combine(): Result<List<NewsArticle>> {
        return find { it is ErrorResult } ?: filterIsInstance<List<NewsArticle>>().flatten()
            .toSuccess()
    }

    fun loadArticle(id: Long): Flow<NewsArticle> {
        return db.newsDao().loadNewsArticle(id).map { it.toModel() }
    }
}
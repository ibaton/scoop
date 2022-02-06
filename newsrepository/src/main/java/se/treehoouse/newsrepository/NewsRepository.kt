package se.treehoouse.newsrepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import se.treehoouse.newsapi.model.Message
import se.treehoouse.newsapi.NewsApiService
import se.treehoouse.newsrepository.converters.*
import se.treehoouse.newsrepository.model.NewsArticle
import se.treehoouse.newsrepository.model.Result
import se.treehoouse.newsstorage.NewsDatabase
import se.treehoouse.newsstorage.model.NewsArticleDB
import java.lang.Exception

class NewsRepository(
    private val api: NewsApiService,
    private val db: NewsDatabase
) {
    fun loadTopics(topics: List<String>): Flow<Result<List<NewsArticle>>> {
        val filteredTopics = topics.filter { it.isNotBlank() }

        return combine(
            filteredTopics.map { loadRemoteTopic(it) }
        ) { results ->
            val allResults = results.combine()
            if(allResults is Result.Data){
                db.newsDao().updateAll(
                    allResults.value.toArticlesDb()
                )
            }
            allResults
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
        return first()
    }

    fun loadArticle(id: Long): Flow<NewsArticle> {
        return db.newsDao().loadNewsArticle(id).map { it.toModel() }
    }
}
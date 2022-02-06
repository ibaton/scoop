package se.treehoouse.newsrepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import se.treehoouse.newsapi.model.Message
import se.treehoouse.newsapi.NewsApiService
import se.treehoouse.newsapi.createNewsApiService
import se.treehoouse.newsrepository.converters.toArticleModels
import se.treehoouse.newsrepository.converters.toFailure
import se.treehoouse.newsrepository.converters.toSuccess
import se.treehoouse.newsrepository.model.NewsArticle
import se.treehoouse.newsrepository.model.Result
import java.lang.Exception

class NewsRepository(
    private val api: NewsApiService = createNewsApiService()
) {
    fun loadTopics(topics: List<String>): Flow<Result<List<NewsArticle>>> {
        val filteredTopics = topics.filter { it.isNotBlank() }

        return combine(
            filteredTopics.map { loadTopic(it) }
        ) { results ->
            results.combine()
        }
    }

    fun loadTopic(topic: String): Flow<Result<List<NewsArticle>>> {
        return loadRemoteTopic(topic)
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
}
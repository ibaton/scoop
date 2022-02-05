package se.treehoouse.newsrepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import se.treehoouse.newsapi.Message
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
    fun loadTopic(topic: String): Flow<Result<List<NewsArticle>>> {
        return loadRemoteTopic(topic)
    }

    private fun loadRemoteTopic(topic: String): Flow<Result<List<NewsArticle>>> {
        return flow {
            try {
                val message: Message.ArticleMessageTO = api.topHeadlines()

                val data: Result.Data<List<NewsArticle>> = message.articles
                    .toArticleModels()
                    .toSuccess()

                emit(data)
            } catch (e: Exception){
                emit(e.toFailure())
            }
        }
    }
}
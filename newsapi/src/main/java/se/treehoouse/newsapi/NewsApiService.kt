package se.treehoouse.newsapi

import retrofit2.http.GET
import retrofit2.http.Query
import se.treehoouse.newsapi.model.Message

/**
 * Retrofit api specification for NewsApi
 */
interface NewsApiService {
    @GET("everything")
    suspend fun everything(@Query("q") query: String, @Query("sources") sources: String? = null, @Query("apiKey") apiKey: String = NEWS_API_KEY): Message.ArticleMessageTO
}
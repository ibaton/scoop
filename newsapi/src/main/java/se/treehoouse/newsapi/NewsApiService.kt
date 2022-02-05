package se.treehoouse.newsapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit api specification for NewsApi
 */
interface NewsApiService {
    @GET("sources")
    suspend fun listSources(@Query("apiKey") apiKey: String = NEWS_API_KEY): Message.SourceMessageTO

    @GET("everything")
    suspend fun everything(@Query("q") query: String, @Query("sources") sources: String? = null, @Query("apiKey") apiKey: String = NEWS_API_KEY): Message.ArticleMessageTO

    @GET("top-headlines")
    suspend fun topHeadlines(@Query("sources") sources: String? = null, @Query("apiKey") apiKey: String = NEWS_API_KEY): Message.ArticleMessageTO
}
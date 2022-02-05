package se.treehoouse.newsapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit api specification for NewsApi
 */
interface NewsApiService {
    @GET("sources")
    fun listSources(@Query("apiKey") apiKey: String = NEWS_API_KEY): Response<Message.SourceMessageTO>

    @GET("everything")
    fun everything(@Query("q") query: String, @Query("sources") sources: String? = null, @Query("apiKey") apiKey: String = NEWS_API_KEY): Response<Message.ArticleMessageTO>

    @GET("top-headlines")
    fun topHeadlines(@Query("sources") sources: String? = null, @Query("apiKey") apiKey: String = NEWS_API_KEY): Response<Message.ArticleMessageTO>
}
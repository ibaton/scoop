package se.treehoouse.newsapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A news articles.
 */
@Serializable
data class NewsArticleTO(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("author") val author: String?,
    @SerialName("url") val url: String,
    @SerialName("urlToImage") val urlToImage: String?,
    @SerialName("publishedAt") val publishedAt: String?,
    @SerialName("source") val source: NewsSourceTO,
    @SerialName("content") val content: String
)
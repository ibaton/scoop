package se.treehoouse.newsapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Messages that maps to the messages received from news api
 */
object Message {
    @Serializable
    data class ArticleMessageTO(
        @SerialName("status") val status: String,
        @SerialName("totalResults") val totalResults: Int,
        @SerialName("articles") val articles: List<NewsArticleTO> = listOf()
    )
}
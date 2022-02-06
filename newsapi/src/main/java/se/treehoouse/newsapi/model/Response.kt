package se.treehoouse.newsapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import se.treehoouse.newsapi.model.NewsArticleTO
import se.treehoouse.newsapi.model.NewsFullSourceTO
import se.treehoouse.newsapi.model.NewsSourceTO

/**
 * Messages that maps to the messages received from news api
 */
object Message {
    @Serializable
    data class SourceMessageTO(
        @SerialName("status") val status: String,
        @SerialName("sources") val sources: List<NewsSourceTO> = listOf()
    )

    @Serializable
    data class ArticleMessageTO(
        @SerialName("status") val status: String,
        @SerialName("totalResults") val totalResults: Int,
        @SerialName("articles") val articles: List<NewsArticleTO> = listOf()
    )
}
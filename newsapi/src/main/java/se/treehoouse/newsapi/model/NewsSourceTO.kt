package se.treehoouse.newsapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A source of news articles.
 */
@Serializable
data class NewsSourceTO(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String,
)
package se.treehoouse.newsapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A source of news articles.
 */
@Serializable
data class NewsFullSourceTO(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val  description: String,
    @SerialName("url") val  url: String,
    @SerialName("category") val  category: String,
    @SerialName("language") val  language: String,
    @SerialName("country") val  country: String
)
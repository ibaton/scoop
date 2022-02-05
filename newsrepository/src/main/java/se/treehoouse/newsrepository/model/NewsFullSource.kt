package se.treehoouse.newsrepository.model

/**
 * A source of news articles.
 */
data class NewsFullSource(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
)
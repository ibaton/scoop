package se.treehoouse.newsrepository.model

/**
 * A news articles.
 */
data class NewsArticle(
    val id: Long,
    val title: String,
    val description: String,
    val author: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String?,
    val source: NewsSource,
    val content: String
)
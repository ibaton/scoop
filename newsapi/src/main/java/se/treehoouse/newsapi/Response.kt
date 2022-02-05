package se.treehoouse.newsapi

import se.treehoouse.newsapi.model.NewsArticleTO
import se.treehoouse.newsapi.model.NewsFullSourceTO
import se.treehoouse.newsapi.model.NewsSourceTO

/**
 * Messages that maps to the messages received from news api
 */
object Message {
    data class SourceMessageTO(val status: String, val sources: List<NewsSourceTO> = listOf())
    data class ArticleMessageTO(val status: String, val articles: List<NewsArticleTO> = listOf())
}
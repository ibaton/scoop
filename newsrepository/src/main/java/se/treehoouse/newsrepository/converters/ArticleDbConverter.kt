package se.treehoouse.newsrepository.converters

import se.treehoouse.newsrepository.model.NewsArticle
import se.treehoouse.newsrepository.model.NewsSource
import se.treehoouse.newsstorage.model.NewsArticleDB
import se.treehoouse.newsstorage.model.NewsSourceDB

fun List<NewsArticle>.toArticlesDb(): List<NewsArticleDB> = map { it.toDb() }

fun NewsArticle.toDb(): NewsArticleDB = NewsArticleDB(
    id = url.hashCode().toLong(),
    title = title,
    description = description,
    author = author,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    source = source.toDb(),
    content = content
)

fun NewsSource.toDb(): NewsSourceDB = NewsSourceDB(
    id = id,
    name = name,
)
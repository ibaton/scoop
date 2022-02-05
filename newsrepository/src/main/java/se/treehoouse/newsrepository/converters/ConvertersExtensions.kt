package se.treehoouse.newsrepository.converters

import se.treehoouse.newsapi.model.NewsArticleTO
import se.treehoouse.newsapi.model.NewsFullSourceTO
import se.treehoouse.newsapi.model.NewsSourceTO
import se.treehoouse.newsrepository.model.NewsArticle
import se.treehoouse.newsrepository.model.NewsFullSource
import se.treehoouse.newsrepository.model.NewsSource

fun NewsSourceTO.toModel() = NewsSource(id, name)

fun NewsFullSourceTO.toModel() = NewsFullSource(
    id = id,
    name = name,
    description = description,
    url = url,
    category = category,
    language = language,
    country = country,
)

fun NewsArticleTO.toModel() = NewsArticle(
    title = title,
    description = description,
    author = author,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    source = source.toModel(),
    content = content,
)
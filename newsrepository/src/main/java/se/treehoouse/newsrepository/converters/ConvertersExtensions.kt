package se.treehoouse.newsrepository.converters

import se.treehoouse.newsapi.model.NewsArticleTO
import se.treehoouse.newsapi.model.NewsFullSourceTO
import se.treehoouse.newsapi.model.NewsSourceTO
import se.treehoouse.newsrepository.model.NewsArticle
import se.treehoouse.newsrepository.model.NewsFullSource
import se.treehoouse.newsrepository.model.NewsSource

fun NewsSourceTO.toModel() = NewsSource(id, name)
fun List<NewsSourceTO>.toSourceModels() = map { it.toModel() }

fun NewsFullSourceTO.toModel() = NewsFullSource(
    id = id,
    name = name,
    description = description,
    url = url,
    category = category,
    language = language,
    country = country,
)
fun List<NewsSourceTO>.toFullSourceModels() = map { it.toModel() }

fun NewsArticleTO.toModel() = NewsArticle(
    id = url.hashCode().toLong(),
    title = title,
    description = description,
    author = author,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    source = source.toModel(),
    content = content,
)
fun List<NewsArticleTO>.toArticleModels() = map { it.toModel() }
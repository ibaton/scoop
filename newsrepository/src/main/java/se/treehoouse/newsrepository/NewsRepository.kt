package se.treehoouse.newsrepository

import se.treehoouse.newsapi.NewsApiService
import se.treehoouse.newsapi.createNewsApiService

class NewsRepository(
    private val api: NewsApiService = createNewsApiService()
) {

}
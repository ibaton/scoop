package se.treehoouse.scoop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import se.treehoouse.newsapi.NewsApiService
import se.treehoouse.newsapi.createNewsApiService
import se.treehoouse.newsrepository.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Singleton
    @Provides
    fun bindNewsRepository(api: NewsApiService): NewsRepository {
        return NewsRepository(api)
    }

    @Singleton
    @Provides
    fun bindNewsApi(): NewsApiService {
        return createNewsApiService()
    }
}
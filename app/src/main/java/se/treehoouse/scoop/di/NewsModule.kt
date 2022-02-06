package se.treehoouse.scoop.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import se.treehoouse.newsapi.NewsApiService
import se.treehoouse.newsapi.createNewsApiService
import se.treehoouse.newsrepository.NewsRepository
import se.treehoouse.newsstorage.NewsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Singleton
    @Provides
    fun bindNewsRepository(api: NewsApiService, db: NewsDatabase): NewsRepository {
        return NewsRepository(api, db)
    }

    @Singleton
    @Provides
    fun bindNewsApi(): NewsApiService {
        return createNewsApiService()
    }

    @Singleton
    @Provides
    fun bindNewsDb(@ApplicationContext context: Context): NewsDatabase {
        return NewsDatabase.buildDatabase(context)
    }
}
package se.treehoouse.newsstorage

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import se.treehoouse.newsstorage.model.NewsArticleDB

@Dao
interface NewsDao {
    @get:Query("SELECT * FROM article")
    val all: Flow<List<NewsArticleDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(articles: List<NewsArticleDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAll(articles: List<NewsArticleDB>)

    @Delete
    suspend fun delete(article: NewsArticleDB)
}
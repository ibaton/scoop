package se.treehoouse.newsstorage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import se.treehoouse.newsstorage.model.NewsArticleDB


@Database(entities = [NewsArticleDB::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, NewsDatabase::class.java, "news.db")
                .build()
    }
}

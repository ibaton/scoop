package se.treehoouse.newsstorage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A source of news articles.
 */
data class NewsSourceDB(
    @ColumnInfo(name = "source_id") val id: String?,
    @ColumnInfo(name = "source_name") val name: String,
)
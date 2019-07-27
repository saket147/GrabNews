package `in`.track.grabnews.database

import `in`.track.grabnews.model.Article
import `in`.track.grabnews.model.HeadLinesDao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun headlinesDao(): HeadLinesDao
}
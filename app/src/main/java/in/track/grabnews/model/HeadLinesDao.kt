package `in`.track.grabnews.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeadLinesDao {
    @get:Query("SELECT * FROM Article")
    val headlinesList: List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles:List<Article>)
}
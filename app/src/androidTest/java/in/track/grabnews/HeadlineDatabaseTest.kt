package `in`.track.grabnews

import `in`.track.grabnews.database.AppDatabase
import `in`.track.grabnews.model.Article
import `in`.track.grabnews.model.HeadLinesDao
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HeadlineDatabaseTest {

    private var headLinesDao: HeadLinesDao? = null
    @Before
    fun setUp(){
        val db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext, AppDatabase::class.java).build()
        headLinesDao = db.headlinesDao()
    }

    @Test
    fun shouldInsertArticle() {
        val article = Article(
            1,
            "CNN",
            "Desc",
            "Latest news",
            "",
            "",
            "",
            ""
        )
        headLinesDao?.insertAll(listOf(article))
        val headLines = headLinesDao?.headlinesList
        Assert.assertEquals(article.author, headLines?.get(0)?.author)
    }

    @Test
    fun shouldFlushAllData(){
        headLinesDao?.nukeTable()
        Assert.assertEquals(headLinesDao?.headlinesList?.size, 0)
    }
}
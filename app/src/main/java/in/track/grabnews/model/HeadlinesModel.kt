package `in`.track.grabnews.model

import androidx.room.*
import java.io.Serializable

data class TopHeadlines (
    val id: Int,
    val status: String?,
    val totalResults: Long?,
    val articles: List<Article>?
)


@Entity
data class Article (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String? = null,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
): Serializable

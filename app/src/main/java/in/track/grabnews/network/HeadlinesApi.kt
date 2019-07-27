package `in`.track.grabnews.network

import `in`.track.grabnews.model.TopHeadlines
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadlinesApi {
    @GET("top-headlines")
    fun getHeadlines(@Query("country") country: String): Observable<TopHeadlines>
}
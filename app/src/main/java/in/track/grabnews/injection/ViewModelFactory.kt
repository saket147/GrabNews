package `in`.track.grabnews.injection

import `in`.track.grabnews.database.AppDatabase
import `in`.track.grabnews.ui.HeadlinesViewModel
import `in`.track.grabnews.ui.WebViewViewModal
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room

class ViewModelFactory(activity: AppCompatActivity): ViewModelProvider.Factory{
    constructor(activity: AppCompatActivity, url: String?) : this(activity) {
        this.url = url
    }
    private var url: String? = null
    private val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "headline").build()
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(HeadlinesViewModel::class.java)) {
            return HeadlinesViewModel(db.headlinesDao()) as T
        }

        if (modelClass.isAssignableFrom(WebViewViewModal::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return url?.let {
                WebViewViewModal(url = it)
            } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
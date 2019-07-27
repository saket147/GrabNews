package `in`.track.grabnews.injection

import `in`.track.grabnews.database.AppDatabase
import `in`.track.grabnews.ui.HeadlinesViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeadlinesViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "headline").build()
            @Suppress("UNCHECKED_CAST")
            return HeadlinesViewModel(db.headlinesDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
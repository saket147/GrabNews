package `in`.track.grabnews.base

import `in`.track.grabnews.injection.component.DaggerViewModelInjector
import `in`.track.grabnews.ui.HeadlinesViewModel
import `in`.track.grabnews.injection.module.NetworkModule
import `in`.track.grabnews.injection.component.ViewModelInjector
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(){

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }
    private fun inject(){
        when(this){
            is HeadlinesViewModel -> injector.inject(this)
        }
    }
}
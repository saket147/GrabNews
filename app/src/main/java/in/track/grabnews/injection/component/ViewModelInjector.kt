package `in`.track.grabnews.injection.component

import `in`.track.grabnews.ui.viewModel.HeadlinesViewModel
import `in`.track.grabnews.injection.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])

interface ViewModelInjector {

    fun inject(headlinesViewModel: HeadlinesViewModel)

    @Component.Builder
    interface Builder{
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
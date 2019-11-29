package kr.co.alex.weathersample.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kr.co.alex.weathersample.WeatherViewModel
import kr.co.alex.weathersample.repository.WeatherRepository
import kr.co.alex.weathersample.repository.WeatherRepositoryImpl
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindsWeatherViewModel(viewModel: WeatherViewModel): ViewModel

    @Binds
    abstract fun bindsRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository
}
//
//@Module
//class ViewModelFactory2 {
//
//    @Provides
//    @IntoMap
//    @ClassKey(WeatherViewModel::class)
//    fun provideWeatherViewModel(repositoryImpl: WeatherRepository) : ViewModel {
//
//        return WeatherViewModel(repositoryImpl)
//    }
//
//    @Provides
//    fun provideViewModelFactory(creator: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
//
//        return ViewModelFactory(creator)
//    }
//}

class ViewModelFactory @Inject constructor(
    private val creator: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        creator[modelClass]?.let { viewModelProvider ->

            @Suppress("UNCHECKED_CAST")
            return viewModelProvider.get() as T
        }?: run { throw RuntimeException("Not found ViewModelProvider") }
    }
}

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

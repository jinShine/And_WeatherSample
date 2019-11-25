package kr.co.alex.weathersample.di

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import kr.co.alex.weathersample.MainActivity
import kr.co.alex.weathersample.WeatherViewModel

@Module
class MainActivityModule(private val activity: MainActivity) {

    @PerActivity
    @Provides
    fun provideWeatherViewModel(): WeatherViewModel {

        return ViewModelProviders.of(activity)[WeatherViewModel::class.java]
    }
}

@PerActivity
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivitySubComponent: AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<MainActivity>
}

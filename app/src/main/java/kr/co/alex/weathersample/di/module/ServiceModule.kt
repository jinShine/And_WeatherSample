package kr.co.alex.weathersample.di.module

import dagger.Module
import dagger.Provides
import kr.co.alex.weathersample.api.WeatherAPI
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideWeatherService() = WeatherAPI.weatherService
}

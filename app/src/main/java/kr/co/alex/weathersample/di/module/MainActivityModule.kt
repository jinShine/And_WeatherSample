package kr.co.alex.weathersample.di.module

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideViewModel(): String = "testMessage"

}

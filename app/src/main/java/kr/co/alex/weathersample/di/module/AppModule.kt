package kr.co.alex.weathersample.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.co.alex.weathersample.MainActivity
import kr.co.alex.weathersample.di.scope.PerMainActivity

@Module
abstract class AppModule {

    @PerMainActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class, ViewModelFactoryModule::class])
    abstract fun bindMainActivity(): MainActivity


}



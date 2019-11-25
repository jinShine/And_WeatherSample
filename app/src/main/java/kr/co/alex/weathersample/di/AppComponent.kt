package kr.co.alex.weathersample.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import kr.co.alex.weathersample.App
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class,
ActivityModule::class])
interface AppComponent {

    fun inject(app: App)
}

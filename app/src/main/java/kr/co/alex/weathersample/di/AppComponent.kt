package kr.co.alex.weathersample.di

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import kr.co.alex.weathersample.App
import kr.co.alex.weathersample.MainActivity


////안드로이드에서 AndroidInjectionModule::class를 넣어줘야된다
//@Component(modules = [AndroidInjectionModule::class, AppModule::class])
//interface AppComponent {
//
//    fun inject(app: App)
//}
//
//@Module
//abstract class AppModule {
//
//    @ContributesAndroidInjector(modules = [MainActivityModule::class])
//    abstract fun bindMainActivity(): MainActivity
//}
//
//@Module
//class MainActivityModule {
//
//    @Provides
//    fun provideTestMessage(): String = "test"
//}

@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent {

    fun inject(app: App)
}

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}

@Module
class MainActivityModule {

    @Provides
    fun provideTestMessage(): String = "123123123"
}

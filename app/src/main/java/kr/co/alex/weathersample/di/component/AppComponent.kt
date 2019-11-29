package kr.co.alex.weathersample.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import kr.co.alex.weathersample.App
import kr.co.alex.weathersample.di.module.AppModule
import kr.co.alex.weathersample.di.module.ServiceModule
import javax.inject.Singleton

//@Singleton
//@Component(modules = [
//    AndroidInjectionModule::class,
//    AppModule::class
//])
//interface AppComponent {
//    fun inject(app: App)
//}

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ServiceModule::class])
interface AppComponent {

    fun inject(app: App)
}

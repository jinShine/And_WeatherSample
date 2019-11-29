package kr.co.alex.weathersample

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kr.co.alex.weathersample.di.component.DaggerAppComponent

import javax.inject.Inject

class App: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().build().inject(this)

    }

}

//class App : Application(), HasAndroidInjector {
//
//    @Inject
//    lateinit var androidInjector: DispatchingAndroidInjector<Any>
//
//    override fun androidInjector(): AndroidInjector<Any> = androidInjector
//
//
//    override fun onCreate() {
//        super.onCreate()
//
//        DaggerAppComponent.builder().build().inject(this)
//    }
//}

package kr.co.alex.weathersample

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kr.co.alex.weathersample.di.DaggerAppComponent
import javax.inject.Inject




class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().build()
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

}

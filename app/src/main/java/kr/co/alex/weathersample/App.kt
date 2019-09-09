package kr.co.alex.weathersample

import android.app.Application


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        println(" launch app")
    }
}

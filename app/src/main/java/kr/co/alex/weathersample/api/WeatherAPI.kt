package kr.co.alex.weathersample.api

import retrofit2.Retrofit

object WeatherAPI {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://weather.naver.com")
        .build()

    val weatherService: WeatherService = retrofit.create(WeatherService::class.java)

}
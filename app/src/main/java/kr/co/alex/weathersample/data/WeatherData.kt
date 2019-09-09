package kr.co.alex.weathersample.data

import androidx.annotation.DrawableRes


data class WeatherData(@DrawableRes val iconUrl: Int,
                       val desc: String,
                       val temp: Int,
                       val percent: Int)

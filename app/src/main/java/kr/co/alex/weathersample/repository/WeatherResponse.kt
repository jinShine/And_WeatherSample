package kr.co.alex.weathersample.repository

import kr.co.alex.weathersample.data.NationalRegion

sealed class WeatherResponse {
    data class Failure(val error: Throwable) : WeatherResponse()
    data class Success(val data: List<NationalRegion>) : WeatherResponse()
}
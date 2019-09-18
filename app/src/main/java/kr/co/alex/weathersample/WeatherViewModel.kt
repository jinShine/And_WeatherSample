package kr.co.alex.weathersample

import androidx.lifecycle.ViewModel
import kr.co.alex.weathersample.Parser.NaverWeatherParser

class WeatherViewModel(private val parser: NaverWeatherParser): ViewModel() {


    fun test() {
        print("Hello")
    }


}
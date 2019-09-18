package kr.co.alex.weathersample.data

import kr.co.alex.weathersample.Parser.Weather
import kr.co.alex.weathersample.adapter.WeatherAdapter.Companion


sealed class WeatherRecyclerType(val spanSize: Int) {

    object Header: WeatherRecyclerType(Companion.FULL_SPAN_SIZE)

    data class Item(val data: Weather): WeatherRecyclerType(Companion.WEATHER_ITEM_SPAN_SIZE)

    data class Region(val data: String): WeatherRecyclerType(Companion.WEATHER_REGION_SPAN_SIZE)

}

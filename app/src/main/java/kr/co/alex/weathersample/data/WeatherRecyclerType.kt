package kr.co.alex.weathersample.data

import kr.co.alex.weathersample.adapter.WeatherAdapter.Companion.FULL_SPAN_SIZE
import kr.co.alex.weathersample.adapter.WeatherAdapter.Companion.WEATHER_ITEM_SPAN_SIZE
import kr.co.alex.weathersample.adapter.WeatherAdapter.Companion.WEATHER_REGION_SPAN_SIZE


sealed class WeatherRecyclerType(val spanSize: Int) {

    object Header : WeatherRecyclerType(FULL_SPAN_SIZE)

    data class Item(val data: Weather) : WeatherRecyclerType(WEATHER_ITEM_SPAN_SIZE)

    data class Region(val data: String) : WeatherRecyclerType(WEATHER_REGION_SPAN_SIZE)

    object Retry : WeatherRecyclerType(FULL_SPAN_SIZE)

}

package kr.co.alex.weathersample.data


sealed class WeatherRecyclerType {

    object Header: WeatherRecyclerType()

    data class Item(val data: WeatherData): WeatherRecyclerType()

}

package kr.co.alex.weathersample.data


data class NationalRegion (
    val regionName: String,
    val morningWeather: Weather,
    val afternoonWeather: Weather
)
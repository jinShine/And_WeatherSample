package kr.co.alex.weathersample

import androidx.lifecycle.ViewModel
import kr.co.alex.weathersample.data.NationalRegion
import kr.co.alex.weathersample.data.WeatherRecyclerType
import kr.co.alex.weathersample.repository.WeatherRepository


class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    init {
        weatherRepository.getWeatherData()
    }

    var weatherCellData = weatherRepository.getWeatherLiveData()

    fun transform(items: List<NationalRegion>): List<WeatherRecyclerType> {
        var list = mutableListOf<WeatherRecyclerType>()

        items.forEach {
            list.add(WeatherRecyclerType.Region(it.regionName))
            list.add(WeatherRecyclerType.Item(it.morningWeather))
            list.add(WeatherRecyclerType.Item(it.afternoonWeather))
        }
        return list
    }
}
package kr.co.alex.weathersample

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kr.co.alex.weathersample.data.NationalRegion
import kr.co.alex.weathersample.data.WeatherRecyclerType
import kr.co.alex.weathersample.repository.WeatherRepository


class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    var weatherCellData: LiveData<List<WeatherRecyclerType>> = Transformations.map(weatherRepository.getWeatherData())

    private fun transform(items: List<NationalRegion>): List<WeatherRecyclerType> {
        var list = mutableListOf<WeatherRecyclerType>()

        items.forEach {
            list.add(WeatherRecyclerType.Region(it.regionName))
            list.add(WeatherRecyclerType.Item(it.morningWeather))
            list.add(WeatherRecyclerType.Item(it.afternoonWeather))
        }

        return list
    }

}
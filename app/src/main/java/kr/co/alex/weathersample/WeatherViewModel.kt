package kr.co.alex.weathersample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kr.co.alex.weathersample.data.NationalRegion
import kr.co.alex.weathersample.data.WeatherRecyclerType
import kr.co.alex.weathersample.repository.WeatherRepository
import kr.co.alex.weathersample.repository.WeatherResponse


class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private var _weatherCellData = MutableLiveData<List<WeatherRecyclerType>>()
    val weatherCellData: LiveData<List<WeatherRecyclerType>> = _weatherCellData

    private var _weatherErrorData = MutableLiveData<String>()
    val weatherErrorData: LiveData<String> = _weatherErrorData

    private val weatherDataObserver = Observer<WeatherResponse> { response ->
        when (response) {
            is WeatherResponse.Success -> {
                _weatherCellData.value = transform(response.data)
            }
            is WeatherResponse.Failure -> {
                _weatherCellData.value = listOf(WeatherRecyclerType.Retry)
                _weatherErrorData.value = response.error.message
            }
        }
    }

    init {
        weatherRepository.getWeatherData()
        weatherRepository.getWeatherLiveData().observeForever(weatherDataObserver)
    }

    fun retryWeatherData() = weatherRepository.getWeatherData()

    private fun transform(items: List<NationalRegion>): List<WeatherRecyclerType> {
        val list = mutableListOf<WeatherRecyclerType>()

        items.forEach {
            list.add(WeatherRecyclerType.Region(it.regionName))
            list.add(WeatherRecyclerType.Item(it.morningWeather))
            list.add(WeatherRecyclerType.Item(it.afternoonWeather))
        }

        return list
    }

    override fun onCleared() {
        weatherRepository.getWeatherLiveData().removeObserver(weatherDataObserver)

        super.onCleared()
    }
}
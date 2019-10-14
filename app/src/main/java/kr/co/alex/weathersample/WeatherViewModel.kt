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

    private fun transform(items: List<NationalRegion>) = mutableListOf<WeatherRecyclerType>()
        .apply {
            add(WeatherRecyclerType.Header)

            items.forEach {
//                it.regionName.let(WeatherRecyclerType::Region).let(this::add)
                add(WeatherRecyclerType.Region(it.regionName))
                add(WeatherRecyclerType.Item(it.morningWeather))
                add(WeatherRecyclerType.Item(it.afternoonWeather))
            }
        }

    override fun onCleared() {
        weatherRepository.getWeatherLiveData().removeObserver(weatherDataObserver)

        super.onCleared()
    }
}
package kr.co.alex.weathersample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.co.alex.weathersample.api.WeatherService
import kr.co.alex.weathersample.data.NationalRegion
import kr.co.alex.weathersample.parser.NaverWeatherParser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface WeatherRepository {

    fun getWeatherData()
    fun getWeatherLiveData(): LiveData<WeatherResponse>
}

class WeatherRepositoryImpl(private val api: WeatherService) : WeatherRepository {

    private val weatherLiveData = MutableLiveData<WeatherResponse>()

    override fun getWeatherLiveData() = weatherLiveData

    override fun getWeatherData() {

        api.getPage().enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                weatherLiveData.postValue(WeatherResponse.Failure(t))
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.body()?.let { body ->
                    val weatherData = NaverWeatherParser(body.string()).getData()
                    weatherLiveData.postValue(WeatherResponse.Success(weatherData))
                }
            }
        })
    }
}
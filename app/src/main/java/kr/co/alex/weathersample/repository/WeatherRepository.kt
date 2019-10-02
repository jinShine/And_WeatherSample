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

    fun getWeatherData(): LiveData<List<NationalRegion>>

}

class WeatherRepositoryImpl(private val api: WeatherService) : WeatherRepository {

    private val weatherLiveData = MutableLiveData<List<NationalRegion>>()

    //TODO: 옵저빙 변수 만들어서 넘겨보기
    override fun getWeatherData(): LiveData<List<NationalRegion>> {
        api.getPage().enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println(" : : $t")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.body()?.let { body ->
                    val weatherData = NaverWeatherParser(body.string()).getData()
                    weatherLiveData.postValue(weatherData)
                }
            }
        })
    }
}
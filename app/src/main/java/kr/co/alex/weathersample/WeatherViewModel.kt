package kr.co.alex.weathersample

import androidx.lifecycle.ViewModel
import kr.co.alex.weathersample.Parser.NaverWeatherParser
import kr.co.alex.weathersample.Parser.RegionWeather
import kr.co.alex.weathersample.api.WeatherAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherViewModel: ViewModel() {

    fun fetchRegionWeather(completion: (List<RegionWeather>) -> Unit) {

        WeatherAPI.weatherService.getPage().enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println(" :" + t.toString())
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.body()?.let { body ->
                    completion(NaverWeatherParser(body.string()).getData())
                }
            }
        })
    }




}
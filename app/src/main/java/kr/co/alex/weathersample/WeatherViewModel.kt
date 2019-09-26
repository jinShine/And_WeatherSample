package kr.co.alex.weathersample

import androidx.lifecycle.ViewModel
import kr.co.alex.weathersample.Parser.NaverWeatherParser
import kr.co.alex.weathersample.api.WeatherAPI
import kr.co.alex.weathersample.data.NationalRegion
import kr.co.alex.weathersample.data.WeatherRecyclerType
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherViewModel : ViewModel() {

    fun fetchRegionWeather(completion: (List<WeatherRecyclerType>) -> Unit) {

        WeatherAPI.weatherService.getPage().enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println(" :$t")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.body()?.let { body ->
                    val weatherData = NaverWeatherParser(body.string()).getData()
                    completion(transform(items = weatherData))
                }
            }
        })
    }

    private fun transform(items: List<NationalRegion>): List<WeatherRecyclerType> {
        var list = mutableListOf<WeatherRecyclerType>()

        items.map {
            list.add(WeatherRecyclerType.Region(it.regionName))
            list.add(WeatherRecyclerType.Item(it.morningWeather))
            list.add(WeatherRecyclerType.Item(it.afternoonWeather))
        }

        return list
    }

}
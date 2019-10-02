package kr.co.alex.weathersample

import androidx.lifecycle.*
import kr.co.alex.weathersample.api.WeatherAPI
import kr.co.alex.weathersample.data.NationalRegion
import kr.co.alex.weathersample.data.WeatherRecyclerType
import kr.co.alex.weathersample.parser.NaverWeatherParser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableLiveData<List<WeatherRecyclerType>>()
    val weatherLiveData: LiveData<List<WeatherRecyclerType>> = _weatherData


    // map, switchmap은 멤버필드에서 로직을 처리해야된다.
    // 다은 곳에서 로직처리를 하게되서 덮어 씌워지게 되면 데이터가 끊긴다.
//    var test2: LiveData<Int> = Transformations.map(_testData) {
//        it?.let { text ->
//            try {
//                text.toInt()
//            } catch (e: Exception) {
//                -1
//            }
//        }?: run { 0 }
//    }
//
//    private val _userId = MutableLiveData<String>()
//    val test3Data: LiveData<Int> = Transformations.switchMap(_userId) {
//
//    }
//
//
//    val errorMessage: MediatorLiveData<String> = MediatorLiveData()
//    private val _testData2 = MutableLiveData<String>()
//
//    init {
//        errorMessage.addSource(_testData) {
//            errorMessage.value = it
//        }
//
//    }

    fun fetchRegionWeather() {

//        _testData.value = "1" // only Main Thread
//        _testData.postValue("1") // using Background Thread


//        testData.value // 값을 가져올수 있고, set 할 수 없음


        WeatherAPI.weatherService.getPage().enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println(" :$t")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.body()?.let { body ->
                    val weatherData = NaverWeatherParser(body.string()).getData()
//                    completion(transform(items = weatherData))
                    _weatherData.postValue(transform(weatherData))

                }
            }
        })
    }

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
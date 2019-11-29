package kr.co.alex.weathersample.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface WeatherService{
    @GET("rgn/cityWetrMain.nhn")
    fun getPage(): Call<ResponseBody>
}

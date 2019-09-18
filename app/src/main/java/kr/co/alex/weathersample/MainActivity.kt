package kr.co.alex.weathersample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.alex.weathersample.Parser.NaverWeatherParser
import kr.co.alex.weathersample.adapter.WeatherAdapter
import kr.co.alex.weathersample.data.WeatherData
import kr.co.alex.weathersample.data.WeatherRecyclerType
import kr.co.alex.weathersample.data.WeatherRegionData
import kr.co.alex.weathersample.api.WeatherAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var adapter: WeatherAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdaptor()

        val viewModel = ViewModelProviders.of(
            this,
            object: ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return WeatherViewModel(NaverWeatherParser()) as T
                }
            }
        ).get(WeatherViewModel::class.java)


        viewModel.test()


//        adapter?.updateAllItems(sampleItems)

        WeatherAPI.weatherService.getPage().enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println(" :" + t.toString())
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println(" :" + response.code())
            }
        })

    }

    fun setupAdaptor() {

        val layoutManager = GridLayoutManager(this, WeatherAdapter.FULL_SPAN_SIZE)
        adapter = WeatherAdapter(layoutManager)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}


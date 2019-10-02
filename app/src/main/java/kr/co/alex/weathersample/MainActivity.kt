package kr.co.alex.weathersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.alex.weathersample.adapter.WeatherAdapter
import kr.co.alex.weathersample.api.WeatherAPI
import kr.co.alex.weathersample.api.WeatherService
import kr.co.alex.weathersample.repository.WeatherRepository
import kr.co.alex.weathersample.repository.WeatherRepositoryImpl

class MainActivity : AppCompatActivity() {

    private var adapter: WeatherAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdaptor()

        val viewModel = ViewModelProviders.of(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return WeatherViewModel(WeatherRepositoryImpl(WeatherAPI.weatherService)) as T
                }
            }
        ).get(WeatherViewModel::class.java)

        viewModel.weatherCellData.observe(this, Observer { weatherList ->
            weatherList?.let {
                adapter?.let {
                    it.updateAllItems(weatherList)
                }
            }

        })
//        viewModel.fetchRegionWeather { list ->
//            adapter?.let {
//                println(list)
//                it.updateAllItems(list)
//            }
//        }
//        viewModel.fetchRegionWeather()
//        viewModel.weatherLiveData.observe(this, Observer { list ->
//            list?.let {
//                adapter?.let {
//                    println("" + list)
//                    it.updateAllItems(list)
//                }
//            }
//        })

    }

    private fun setupAdaptor() {
        val layoutManager = GridLayoutManager(this, WeatherAdapter.FULL_SPAN_SIZE)
        adapter = WeatherAdapter(layoutManager)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}


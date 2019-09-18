package kr.co.alex.weathersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.alex.weathersample.Parser.RegionWeather
import kr.co.alex.weathersample.Parser.Weather
import kr.co.alex.weathersample.adapter.WeatherAdapter
import kr.co.alex.weathersample.data.WeatherRecyclerType


class MainActivity : AppCompatActivity() {

    private var adapter: WeatherAdapter? = null
    private val items = mutableListOf<WeatherRecyclerType>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdaptor()

        val viewModel = ViewModelProviders.of(
            this,
            object: ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return WeatherViewModel() as T
                }
            }
        ).get(WeatherViewModel::class.java)

        viewModel.fetchRegionWeather { list ->

//            adapter?.let {
//                runOnUiThread {
//                    it.updateAllItems(items)
//                }
//
//            }

        }

    }

    fun setupAdaptor() {
        val layoutManager = GridLayoutManager(this, WeatherAdapter.FULL_SPAN_SIZE)
        adapter = WeatherAdapter(layoutManager)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}


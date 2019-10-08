package kr.co.alex.weathersample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.alex.weathersample.adapter.WeatherAdapter
import kr.co.alex.weathersample.api.WeatherAPI
import kr.co.alex.weathersample.repository.WeatherRepositoryImpl

class MainActivity : AppCompatActivity() {

    private var adapter: WeatherAdapter? = null

    @Suppress("UNCHECKED_CAST")
    private val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            return WeatherViewModel(WeatherRepositoryImpl(WeatherAPI.weatherService)) as T
        }
    }

//    @Suppress("UNCHECKED_CAST")
//    private val viewModel: WeatherViewModel by lazy {
//        ViewModelProviders.of(
//            this, factory
//        ).get(WeatherViewModel::class.java)
//    }

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProviders.of(this, factory)[WeatherViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdaptor()

        viewModel.weatherCellData.observe(this, Observer { response ->
            adapter?.updateAllItems(response)
        })

        viewModel.weatherErrorData.observe(this, Observer { errorMsg ->
            errorMsg?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupAdaptor() {
        val layoutManager = GridLayoutManager(this, WeatherAdapter.FULL_SPAN_SIZE)
        adapter = WeatherAdapter(layoutManager)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}

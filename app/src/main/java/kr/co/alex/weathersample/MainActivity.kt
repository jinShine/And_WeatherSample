package kr.co.alex.weathersample

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.alex.weathersample.adapter.WeatherAdapter
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), WeatherAdapter.CellEvents {

    private var adapter: WeatherAdapter? = null

//    @Suppress("UNCHECKED_CAST")
//    private val factory = object : ViewModelProvider.Factory {
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            return WeatherViewModel(WeatherRepositoryImpl(WeatherAPI.weatherService)) as T
//        }
//    }

//    @Inject
//    @Named("testMessage")
//    lateinit var testValue: String

    @Inject
    lateinit var dummyValue: String


//    @Inject
//    lateinit var viewModel: WeatherViewModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProviders.of(this, factory)[WeatherViewModel::class.java]
    }

//    @Inject
//    @Named("WeatherViewModel")
//    lateinit var viewModel: WeatherViewModel

//    private val viewModel: WeatherViewModel by lazy {
//        ViewModelProviders.of(this, factory)[WeatherViewModel::class.java]
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdaptor()

//        println(" ${testValue}")
        println(" ${dummyValue}")

        viewModel.weatherCellData.observe(this, Observer { response ->
            adapter?.updateAllItems(response)
        })

        viewModel.weatherErrorData.observe(this, Observer { errorMsg ->
            errorMsg?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        viewModel.swipeResult.observe(this, Observer {
            swipeRefresh.isRefreshing = it
        })

        swipeRefresh.setOnRefreshListener {
            viewModel.loadWeatherData()
        }
    }

    private fun setupAdaptor() {
        val layoutManager = GridLayoutManager(this, WeatherAdapter.FULL_SPAN_SIZE)
        adapter = WeatherAdapter(layoutManager, this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onRetryClicked() {
        viewModel.loadWeatherData(R.string.weather_loading_retry)
    }
}

package kr.co.alex.weathersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.alex.weathersample.adapter.WeatherAdapter
import kr.co.alex.weathersample.data.WeatherData
import kr.co.alex.weathersample.data.WeatherRecyclerType

class MainActivity : AppCompatActivity() {

    companion object {
        private const val SPAN_COUNT = 2
    }

    private val adapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdaptor()


        val sampleItems = listOf(
            WeatherRecyclerType.Header,
            WeatherRecyclerType.Item(
                WeatherData(
                    R.mipmap.ic_launcher,
                    "비가오네요",
                    25,
                    80
                )
            ),
            WeatherRecyclerType.Item(
                WeatherData(
                    R.mipmap.ic_launcher,
                    "비가오네요2",
                    30,
                    100
                )
            )
        )

        adapter.updateAllItems(sampleItems)
    }

    fun setupAdaptor() {

        recyclerView.layoutManager = GridLayoutManager(this, SPAN_COUNT).apply {

            this.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

                override fun getSpanSize(position: Int): Int {
                    return when (adapter.getItemViewType(position)) {
                        WeatherAdapter.VIEW_TYPE_HEADER -> 2
                        WeatherAdapter.VIEW_TYPE_ITEM -> 1
                        else -> throw RuntimeException("")
                    }
                }
            }
        }

        recyclerView.adapter = adapter
    }
}

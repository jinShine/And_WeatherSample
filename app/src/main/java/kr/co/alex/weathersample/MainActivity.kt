package kr.co.alex.weathersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.alex.weathersample.adapter.WeatherAdapter
import kr.co.alex.weathersample.data.WeatherData
import kr.co.alex.weathersample.data.WeatherRecyclerType
import kr.co.alex.weathersample.data.WeatherRegionData

class MainActivity : AppCompatActivity() {

    private var adapter: WeatherAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdaptor()


        val sampleItems = listOf(
            WeatherRecyclerType.Header,
            WeatherRecyclerType.Region(WeatherRegionData(("서울\n경기"))),
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
            ),
            WeatherRecyclerType.Region(WeatherRegionData(("서해\n5도"))),
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

        adapter?.updateAllItems(sampleItems)
    }

    fun setupAdaptor() {

        val layoutManager = GridLayoutManager(this, WeatherAdapter.FULL_SPAN_SIZE)
        adapter = WeatherAdapter(layoutManager)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}

package kr.co.alex.weathersample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
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


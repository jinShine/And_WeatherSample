package kr.co.alex.weathersample.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_weather_region.*
import kr.co.alex.weathersample.data.WeatherRecyclerType

class WeatherRegionViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: WeatherRecyclerType) {

        if (item !is WeatherRecyclerType.Region) {
            return
        }

        region.text = item.data.replace(" ", "\n")

    }
}
package kr.co.alex.weathersample.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_weather_region.view.*
import kr.co.alex.weathersample.data.WeatherRecyclerType

class WeatherRegionViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(item: WeatherRecyclerType) {

        if (item !is WeatherRecyclerType.Region) {
            return
        }

        itemView.region.text = item.data

    }
}
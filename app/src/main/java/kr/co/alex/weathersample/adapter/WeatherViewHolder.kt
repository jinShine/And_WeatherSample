package kr.co.alex.weathersample.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.viewholder_weather.view.*
import kr.co.alex.weathersample.data.WeatherRecyclerType

class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: WeatherRecyclerType) {

        if (item !is WeatherRecyclerType.Item) {
            return
        }

        with(item.data) {

            // Weather Icon
            Glide.with(itemView).load(iconUrl).into(itemView.weatherIcon)

            // Weather Desc
            itemView.weatherDesc.text = status

            // Weather Temperature
            itemView.weatherTemp.text = "기온 : $temperature"

            // Weather Percent
            itemView.weatherPercent.text = "강수확률 : $chanceOfRain"
        }

    }

}

package kr.co.alex.weathersample.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_loading.*
import kr.co.alex.weathersample.data.WeatherRecyclerType

class WeatherLoadingViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: WeatherRecyclerType) {

        if (item !is WeatherRecyclerType.Loading) {
            return
        }

        loadingDes.setText(item.data)
    }
}
package kr.co.alex.weathersample.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_retry.*

class WeatherRetryViewHolder(
    override val containerView: View,
    private val events: WeatherAdapter.CellEvents
) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind() {
        retryButton.setOnClickListener {
            events.onRetryClicked()
        }
    }
}
package kr.co.alex.weathersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.alex.weathersample.R
import kr.co.alex.weathersample.data.WeatherRecyclerType


class WeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        const val VIEW_TYPE_HEADER = R.layout.viewholder_weather_header
        const val VIEW_TYPE_ITEM = R.layout.viewholder_weather
    }

    private val items = mutableListOf<WeatherRecyclerType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return when (viewType) {
            VIEW_TYPE_HEADER -> WeatherHeaderViewHolder(view)
            VIEW_TYPE_ITEM -> WeatherViewHolder(view)
            else -> throw RuntimeException("Invalid Type")
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when (items[position]) {
            is WeatherRecyclerType.Header -> VIEW_TYPE_HEADER
            is WeatherRecyclerType.Item -> VIEW_TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {

        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeatherViewHolder -> holder.bind(items[position])
        }
    }

    fun updateAllItems(items: List<WeatherRecyclerType>) {
        this.items.clear()
        this.items.addAll(items)

        notifyDataSetChanged()
    }






}

package kr.co.alex.weathersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.alex.weathersample.R
import kr.co.alex.weathersample.data.WeatherRecyclerType


class WeatherAdapter(layoutManager: GridLayoutManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val FULL_SPAN_SIZE = 10
        const val WEATHER_ITEM_SPAN_SIZE = 4
        const val WEATHER_REGION_SPAN_SIZE = 2

        const val VIEW_TYPE_HEADER = R.layout.viewholder_weather_header
        const val VIEW_TYPE_ITEM = R.layout.viewholder_weather
        const val VIEW_TYPE_REGION = R.layout.viewholder_weather_region
    }

    private val items = mutableListOf<WeatherRecyclerType>()

    init {
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return items[position].spanSize
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return when (viewType) {
            VIEW_TYPE_HEADER -> WeatherHeaderViewHolder(view)
            VIEW_TYPE_ITEM -> WeatherViewHolder(view)
            VIEW_TYPE_REGION -> WeatherRegionViewHolder(view)
            else -> throw RuntimeException("Invalid Type")
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when (items[position]) {
            is WeatherRecyclerType.Header -> VIEW_TYPE_HEADER
            is WeatherRecyclerType.Item -> VIEW_TYPE_ITEM
            is WeatherRecyclerType.Region -> VIEW_TYPE_REGION
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeatherViewHolder -> holder.bind(items[position])
            is WeatherRegionViewHolder -> holder.bind(items[position])
        }
    }

    fun updateAllItems(items: List<WeatherRecyclerType>) {
        this.items.clear()
        this.items.addAll(items)

        notifyDataSetChanged()
    }






}

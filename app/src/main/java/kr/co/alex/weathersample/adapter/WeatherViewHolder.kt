package kr.co.alex.weathersample.adapter

import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_weather.*
import kotlinx.android.synthetic.main.viewholder_weather.view.*
import kr.co.alex.weathersample.R
import kr.co.alex.weathersample.data.WeatherRecyclerType

class WeatherViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var context = itemView.context

    fun bind(item: WeatherRecyclerType) {

        if (item !is WeatherRecyclerType.Item) {
            return
        }

        with(item.data) {

            // Weather Icon
            Glide.with(itemView).load(iconUrl).into(itemView.weatherIcon)

            // Weather Desc
            weatherDesc.text = status

            // Weather Temperature
            weatherTemp.text =
                setupTemperature(context.getString(R.string.temp_format, temperature))

            // Weather Percent
            weatherPercent.text =
                setupPercent(context.getString(R.string.main_format, chanceOfRain))
        }

    }

    private fun setupTemperature(text: String): SpannableStringBuilder {

        val sp = SpannableStringBuilder(text)
        val start = text.splitSpaceToRange().first
        val end = text.splitSpaceToRange().last

        sp.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.temp_red)),
            start,
            end,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        sp.setSpan(
            StyleSpan(Typeface.BOLD),
            start,
            end,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return sp
    }

    private fun setupPercent(text: String): SpannableStringBuilder {

        val sp = SpannableStringBuilder(text)
        val start = text.splitSpaceToRange().first
        val end = text.splitSpaceToRange().last

        sp.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.weather_info_text_red)),
            start,
            end,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        sp.setSpan(
            StyleSpan(Typeface.BOLD),
            start,
            end,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return sp
    }

}


//Extension
fun String.splitSpaceToRange(): IntRange {
    return this.indexOf(" ").let {
        val startIndex = it + 1
        val endIndex = this.length
        IntRange(startIndex, endIndex)
    }
}
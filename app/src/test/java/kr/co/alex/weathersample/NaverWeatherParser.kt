package kr.co.alex.weathersample

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.File

class NaverWeatherParser {

    companion object {
        const val QUERY_ROWS = "#container #content table tbody tr"
        const val QUERY_ROWS_TH_A = "th a"
        const val QUERY_ROWS_TD = "td"
        const val QUERY_ROWS_P_IMG = "p img"
        const val QUERY_ROWS_UL_UI = "ul li"
        const val QUERY_ROWS_SRC = "src"
        const val QUERY_ROWS_TEMP = ".temp"
        const val QUERY_ROWS_RAIN = ".rain"

        const val QUERY_HEADER = "#header .lnb"
        const val QUERY_HEADER_DATE = ".lnb_date"
        const val QUERY_HEADER_BLIND = ".blind"
    }

    val document: Document

    constructor() {
        document = Jsoup.connect("https://weather.naver.com/rgn/cityWetrMain.nhn").get()
    }

    constructor(file: File) {
        document = Jsoup.parse(file, "utf-8")
    }

    fun getWeatherRows() = document.select(QUERY_ROWS)

    fun getNowDateHeader() = document.select(QUERY_HEADER)

    fun getNoWDate(): String {
        return getNowDateHeader()
            .let {
                val dateQuery = it.select(QUERY_HEADER_DATE)
                dateQuery.forEach { it.select(QUERY_HEADER_BLIND).remove() }
                dateQuery.text()
            }
    }

    fun getRegion(el: Element) = el.select(QUERY_ROWS_TH_A).text()

    fun getMorningWeather(el: Element) = getWeather(el, true)

    fun getAfternoonWeather(el: Element) = getWeather(el, false)

    private fun getWeather(el: Element, isMorning: Boolean): Weather {
        val index = if (isMorning) 0 else 1
        val baseQuery = el.select(QUERY_ROWS_TD)[index]

        return Weather(
            iconUrl = baseQuery.select(QUERY_ROWS_P_IMG).attr(QUERY_ROWS_SRC),
            status = baseQuery.select(QUERY_ROWS_UL_UI).first().text(),
            temperature = baseQuery.select(QUERY_ROWS_UL_UI).last().select(
                QUERY_ROWS_TEMP
            ).text(),
            chanceOfRain = baseQuery.select(QUERY_ROWS_UL_UI).last().select(
                QUERY_ROWS_RAIN
            ).text()
        )
    }

    fun getData(): List<RegionWeather> {
        return getWeatherRows()
            .map {
                val regionName = getRegion(it)
                val morningWeather = getMorningWeather(it)
                val afternoonWeather = getAfternoonWeather(it)

                return@map Triple(regionName, morningWeather, afternoonWeather)
            }.map {
                var (region, morning, afternoon) = it

                RegionWeather(
                    regionName = region,
                    morningWeather = morning,
                    afternoonWeather = afternoon
                )
            }
    }


}

data class RegionWeather(
    val regionName: String,
    val morningWeather: Weather,
    val afternoonWeather: Weather
)

data class Weather(
    val iconUrl: String,
    val status: String,
    val temperature: String,
    val chanceOfRain: String
)
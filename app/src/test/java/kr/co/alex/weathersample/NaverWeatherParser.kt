package kr.co.alex.weathersample

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.File

class NaverWeatherParser {

    companion object {
        const val BASE_URL = "https://weather.naver.com/rgn/cityWetrMain.nhn"

        const val CHARSET_NAME = "utf-8"

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
        document = Jsoup.connect(BASE_URL).get()
    }

    constructor(file: File) {
        document = Jsoup.parse(file, CHARSET_NAME)
    }


    private fun getNowDateHeader(): Elements =
        document.select(QUERY_HEADER)

    fun getWeatherRows(): Elements =
        document.select(QUERY_ROWS)

    fun getNoWDate(): String {
        return getNowDateHeader()
            .let { el ->
                val dateQuery = el.select(QUERY_HEADER_DATE)
                dateQuery.forEach { it.select(QUERY_HEADER_BLIND).remove() }
                dateQuery.text()
            }
    }

    fun getRegion(el: Element): String =
        el.select(QUERY_ROWS_TH_A).text()

//    private fun getWeather(
//        el: Element,
//        isMorning: Elements.() -> Element
//    ): Weather {
//        return el.select(QUERY_ROWS_TD).isMorning().let { weatherParser(it) }
//    }

    private fun getWeather(
        el: Element,
        isMorning: Boolean
    ): Weather {
        return if ( isMorning ) {
            el.select(QUERY_ROWS_TD).first().let { weatherParser(it) }
        } else {
            el.select(QUERY_ROWS_TD).last().let { weatherParser(it) }
        }

    }

    fun getData() =
        getWeatherRows().map {
            RegionWeather(
                regionName = getRegion(it),
                morningWeather = getWeather(it, isMorning = true),
                afternoonWeather = getWeather(it, isMorning = false)
            )
        }


    private fun weatherParser(el: Element) =
        Weather(
            iconUrl = el.select(QUERY_ROWS_P_IMG).attr(QUERY_ROWS_SRC),
            status = el.select(QUERY_ROWS_UL_UI).first().text(),
            temperature = el.select(QUERY_ROWS_UL_UI).last().select(QUERY_ROWS_TEMP).text(),
            chanceOfRain = el.select(QUERY_ROWS_UL_UI).last().select(QUERY_ROWS_RAIN).text()
        )


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
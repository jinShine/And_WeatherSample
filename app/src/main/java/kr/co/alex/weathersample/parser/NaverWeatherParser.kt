package kr.co.alex.weathersample.parser

import kr.co.alex.weathersample.data.NationalRegion
import kr.co.alex.weathersample.data.Weather
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.File

class NaverWeatherParser {

    private enum class Meridiem {
        AM,
        PM
    }

    companion object {
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

    var document: Document

    constructor(file: File) {
        document = Jsoup.parse(
            file,
            CHARSET_NAME
        )
    }

    constructor(response: String) {
        document = Jsoup.parse(response)
    }

    private fun getNowDateHeader(): Elements = document.select(QUERY_HEADER)

    fun getWeatherRows(): Elements = document.select(QUERY_ROWS)

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

    private fun getWeather(
        el: Element,
        meridiem: Meridiem
    ): Weather {

        return when (meridiem) {
            Meridiem.AM -> el.select(QUERY_ROWS_TD).first().let(::weatherParser)
            Meridiem.PM -> el.select(QUERY_ROWS_TD).last().let(::weatherParser)
        }

    }

    fun getData() =
        getWeatherRows().map {
            NationalRegion(
                regionName = getRegion(it),
                morningWeather = getWeather(it, Meridiem.AM),
                afternoonWeather = getWeather(it, Meridiem.PM)
            )
        }

    private fun weatherParser(el: Element) = Weather(
        iconUrl = el.select(QUERY_ROWS_P_IMG).attr(QUERY_ROWS_SRC),
        status = el.select(QUERY_ROWS_UL_UI).first().text(),
        temperature = el.select(QUERY_ROWS_UL_UI).last().select(QUERY_ROWS_TEMP).text(),
        chanceOfRain = el.select(QUERY_ROWS_UL_UI).last().select(QUERY_ROWS_RAIN).text()
    )

}

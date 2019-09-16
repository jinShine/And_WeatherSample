package kr.co.alex.weathersample

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.File

class NaverWeatherParser {

    val document: Document

    constructor() {
        document = Jsoup.connect("https://weather.naver.com/rgn/cityWetrMain.nhn").get()
    }

    constructor(file: File) {
        document = Jsoup.parse(file, "utf-8")
    }

    fun getData(): List<RegionWeather> {
        return getWeatherRows()
            .map {
                val regionName = it.select("th a").text()
                val morningWeather = Weather(
                    iconUrl = it.select("td").first().select("p img").attr("src"),
                    status = it.select("td").first().select("ul li").first().text(),
                    temperature = it.select("td").first().select("ul li").last().select(".temp").text(),
                    chanceOfRain = it.select("td").first().select("ul li").last().select(".rain").text()
                )

                val afternoonWeather = Weather(
                    iconUrl = it.select("td").last().select("p img").attr("src"),
                    status = it.select("td").last().select("ul li").first().text(),
                    temperature = it.select("td").last().select("ul li").last().select(".temp").text(),
                    chanceOfRain = it.select("td").last().select("ul li").last().select(".rain").text()
                )

                RegionWeather(
                    regionName = regionName,
                    morningWeather = morningWeather,
                    afternoonWeather = afternoonWeather
                )
            }
    }


    fun getWeatherRows(): Elements {
        //id는 #을 붙혀야됨
        return document.select("#container #content table tbody tr")
    }

}

data class RegionWeather(
    val regionName: String,
    val morningWeather: Weather,
    val afternoonWeather: Weather
)

data class Weather (
    val iconUrl: String,
    val status: String,
    val temperature: String,
    val chanceOfRain: String
)
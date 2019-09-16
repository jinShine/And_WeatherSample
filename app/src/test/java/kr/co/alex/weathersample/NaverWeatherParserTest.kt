package kr.co.alex.weathersample

import org.jsoup.nodes.Document
import org.junit.Test
import org.junit.Assert.*
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NaverWeatherParserTest {

	@Test
	fun testIsShownDocumentFromNetwork() {
		val doc: Document =  NaverWeatherParser().document
		println(doc)
		assertEquals(true, doc.toString().contains("<!DOCTYPE html>", true))
	}

	@Test
	fun testIsShownDocumentFromFile() {
		val file = getWeatherHTML()
		val doc = NaverWeatherParser(file).document
		println(doc)
		assertEquals(true, doc.toString().contains("<!DOCTYPE html", true))
	}

	@Test
	fun testGetWeatherRows() {
		val els = getNaverWeatherParserFromFile().getWeatherRows()
		assertEquals(true, els.count() > 0)
	}

	@Test
	fun testGetWeatherHtml() {
		val file = getWeatherHtml()

		assertEquals(true, file.isFile)
	}

	@Test
	fun testGetWeatherData() {
		val parser = getNaverWeatherParserFromFile()
		val data = parser.getData()
		assertEquals(true, data.isNotEmpty())
	}

	private fun getNaverWeatherParserFromFile(): NaverWeatherParser {
		val file = getWeatherHTML()
		return NaverWeatherParser(file)
	}

	fun getWeatherHTML() =
		File(this.javaClass.classLoader.getResource("naverWeather.html").file)


}

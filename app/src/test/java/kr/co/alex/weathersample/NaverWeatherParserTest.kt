package kr.co.alex.weathersample

import org.jsoup.nodes.Document
import org.junit.Test
import org.junit.Assert.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

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
		val file = getWeatherHTML()
		assertEquals(true, file.isFile)
	}


	@Test
	fun tesGetRegion() {
		val parser = getNaverWeatherParserFromFile()
		val data = parser.getWeatherRows()
			.map { parser.getRegion(it) }

		assertEquals(true, data.isNotEmpty())
	}

	@Test
	fun testValidateWeather() {
		val parser = getNaverWeatherParserFromFile()
		val data = parser.getData()

		assertEquals(true, data.isNotEmpty())
	}

	@Test
	fun testGetNowDate() {
		val parser = NaverWeatherParser()
		val date = parser.getNoWDate()

		assertEquals(date, nowDate())
	}

	private fun getNaverWeatherParserFromFile(): NaverWeatherParser {
		val file = getWeatherHTML()
		return NaverWeatherParser(file)
	}

	fun getWeatherHTML() =
		File(this.javaClass.classLoader.getResource("naverWeather.html").file)


	fun nowDate(): String {

		val dateFormat = SimpleDateFormat("MM.dd")
		var date = dateFormat.format(Date())

		val cal = Calendar.getInstance()
		val dayOfWeek = when (cal.get(Calendar.DAY_OF_WEEK)) {
			1 -> "일"
			2 -> "월"
			3 -> "화"
			4 -> "수"
			5 -> "목"
			6 -> "금"
			else -> { "토" }
		}

		val result = String.format("%s(%s)",date,dayOfWeek)

		return result
	}

}

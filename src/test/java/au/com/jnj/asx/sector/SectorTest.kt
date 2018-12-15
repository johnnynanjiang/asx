package au.com.jnj.asx.sector

import jdk.nashorn.internal.parser.JSONParser
import org.json.JSONObject
import org.jsoup.Jsoup
import org.junit.Test
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.junit.Assert.*

/**
 * Created by nanjiang on 15/12/18.
 */

class SectorTest {
    @Test
    fun testGettingSectorPerformance() {
        val doc: Document = Jsoup.connect("https://au.spindices.com/indices/equity/sp-asx-200-utilities-sector").timeout(10 * 1000).get()
        val bodyTag = doc.getElementsByTag("body")[0] // TODO: get index data from body text directly for fault tolerance
        val scriptTags = doc.getElementsByTag("script")
        val scriptTagWithData = scriptTags[11]
        val scriptCodeBlock = scriptTagWithData.childNodes()[0].attributes().get("data")
        val indexDataString = scriptCodeBlock.substringAfter("var indexData = ").substringBefore(";")
        val jsonObject = JSONObject(indexDataString)
        val indexPerformanceHolder = jsonObject.get("indexPerformanceHolder") as JSONObject
        val indexPerformance = indexPerformanceHolder.get("indexPerformance") as JSONObject
        val priceReturn = indexPerformance.get("priceReturn") as JSONObject
        val dailyReturn = priceReturn.get("dailyReturn") as Double
        val monthToDateReturn = priceReturn.get("monthToDateReturn") as Double
        val quarterToDateReturn = priceReturn.get("quarterToDateReturn") as Double
        val yearToDateReturn = priceReturn.get("yearToDateReturn") as Double
        val oneYearReturn = priceReturn.get("oneYearReturn") as Double
        val threeYearReturn = priceReturn.get("threeYearReturn") as Double
        val fiveYearReturn = priceReturn.get("fiveYearReturn") as Double
        val tenYearReturn = priceReturn.get("tenYearReturn") as Double

        assertEquals(23, scriptTags.size)
        assertTrue(indexDataString.startsWith("{\"status\":true,"))
        assertTrue(oneYearReturn.toString().startsWith("-13.56"))
        assertTrue(threeYearReturn.toString().startsWith("3.55"))
        assertTrue(fiveYearReturn.toString().startsWith("6.67"))
        assertTrue(tenYearReturn.toString().startsWith("6.116"))
    }
}
package au.com.jnj.asx.sector

import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by nanjiang on 16/12/18.
 */

class Sector {
    fun getPerformanceForSector(sectorDefinition: SectorDefinitions): SectorPerformance {
        return SectorPerformance(
                sectorDefinition,
                returns = getReturnsFromUrl(sectorDefinition.url)
        )
    }

    internal fun getReturnsFromUrl(url: String): SectorPerformance.Returns =
            getReturnsFromJsonString(getPerformanceJsonStringFromUrl(url))

    internal fun getPerformanceJsonStringFromUrl(url: String): String {
        val doc: Document = Jsoup.connect(url).timeout(10 * 1000).get()
        //val bodyTag = doc.getElementsByTag("body")[0] // TODO: get index data from body text directly for fault tolerance
        val scriptTags = doc.getElementsByTag("script")
        val scriptTagWithData = scriptTags[11]
        val scriptCodeBlock = scriptTagWithData.childNodes()[0].attributes().get("data")

        return scriptCodeBlock.substringAfter("var indexData = ").substringBefore(";")
    }

    internal fun getReturnsFromJsonString(jsonString: String): SectorPerformance.Returns {
        val jsonObject = JSONObject(jsonString)
        val indexPerformanceHolder = jsonObject.get("indexPerformanceHolder") as JSONObject
        val indexPerformance = indexPerformanceHolder.get("indexPerformance") as JSONObject
        val priceReturn = indexPerformance.get("priceReturn") as JSONObject

        return SectorPerformance.Returns(
                priceReturn.get("dailyReturn") as Double,
                priceReturn.get("monthToDateReturn") as Double,
                priceReturn.get("quarterToDateReturn") as Double,
                priceReturn.get("yearToDateReturn") as Double,
                priceReturn.get("oneYearReturn") as Double,
                priceReturn.get("threeYearReturn") as Double,
                priceReturn.get("fiveYearReturn") as Double,
                priceReturn.get("tenYearReturn") as Double
        )
    }
}
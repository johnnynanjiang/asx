package au.com.jnj.asx.sector

import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by nanjiang on 16/12/18.
 */

data class SectorPerformance(val datetime: Double = 0.0,
                             val returns: Returns) {

    data class Returns(val dailyReturn: Double = 0.0,
                       val monthToDateReturn: Double = 0.0,
                       val quarterToDateReturn: Double = 0.0,
                       val yearToDateReturn: Double = 0.0,
                       val oneYearReturn: Double = 0.0,
                       val threeYearReturn: Double = 0.0,
                       val fiveYearReturn: Double = 0.0,
                       val tenYearReturn: Double = 0.0)
}

class Sector {
    companion object {
        fun getJsonStringFromUrl(url: String) : String {
            val doc: Document = Jsoup.connect(url).timeout(10 * 1000).get()
            val bodyTag = doc.getElementsByTag("body")[0] // TODO: get index data from body text directly for fault tolerance
            val scriptTags = doc.getElementsByTag("script")
            val scriptTagWithData = scriptTags[11]
            val scriptCodeBlock = scriptTagWithData.childNodes()[0].attributes().get("data")

            return scriptCodeBlock.substringAfter("var indexData = ").substringBefore(";")
        }

        fun getSectorPerformanceDetailsFromJsonString(jsonString: String) : SectorPerformance {
            val jsonObject = JSONObject(jsonString)
            val indexPerformanceHolder = jsonObject.get("indexPerformanceHolder") as JSONObject
            val indexPerformance = indexPerformanceHolder.get("indexPerformance") as JSONObject
            val priceReturn = indexPerformance.get("priceReturn") as JSONObject

            return SectorPerformance(0.0,
                    SectorPerformance.Returns(
                            priceReturn.get("dailyReturn") as Double,
                            priceReturn.get("monthToDateReturn") as Double,
                            priceReturn.get("quarterToDateReturn") as Double,
                            priceReturn.get("yearToDateReturn") as Double,
                            priceReturn.get("oneYearReturn") as Double,
                            priceReturn.get("threeYearReturn") as Double,
                            priceReturn.get("fiveYearReturn") as Double,
                            priceReturn.get("tenYearReturn") as Double
                    ))
        }
    }
}
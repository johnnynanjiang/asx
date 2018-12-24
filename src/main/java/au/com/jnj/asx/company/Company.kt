package au.com.jnj.asx.company

import au.com.jnj.asx.sector.NOT_APPLICABLE
import au.com.jnj.asx.util.WebCrawler
import org.jsoup.nodes.TextNode
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Created by nanjiang on 22/12/18.
 */

class Company(val name: String = NOT_APPLICABLE, val stockCode: String = NOT_APPLICABLE) {

    fun fetchStockDetailsByStockCode(stockCode: String): StockDetails =
            fetchStockDetailsFromStockCode(stockCode)

    internal fun getUrlFromStockCode(stockCode: String): String =
            "https://www.marketindex.com.au/sites/default/files/historical-data/${stockCode.toUpperCase()}.csv"

    internal fun getLatestPriceDate(csvRows: List<String>): LocalDate? {
        if (csvRows.count() > 1) {
            val csvRow = csvRows[1].split(",")
            if (csvRow.count() > 0) {
                return LocalDate.parse(csvRow[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            }
        }

        return null
    }

    private fun fetchStockDetailsFromStockCode(stockCode: String): StockDetails {
        val doc = WebCrawler.fetchFromUrl(getUrlFromStockCode(stockCode))
        val csvString = (doc.childNodes()[0].childNodes()[1].childNodes()[0] as TextNode).text()
        val csvRows = csvString.split(" ")
        val latestPriceDate = getLatestPriceDate(csvRows)
        return StockDetails(stockCode, latestPriceDate)
    }

    data class StockDetails(val stockCode: String = NOT_APPLICABLE,
                            val latestPriceDate: LocalDate?,
                            val oneYearReturn: String = NOT_APPLICABLE,
                            val oneYearReturnVs200: String = NOT_APPLICABLE,
                            val oneYearReturnVsSector: String = NOT_APPLICABLE)
}
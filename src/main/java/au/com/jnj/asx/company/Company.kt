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
            extractStockDetailsFromCsvString(
                    fetchStockPriceHistoryInCsvStringFromStockCode(stockCode))

    internal fun extractStockDetailsFromCsvString(csvString: String): StockDetails {
        val csvRows = csvString.split(" ")
        val latestPriceDate = getLatestPriceDate(csvRows)
        return StockDetails(stockCode, latestPriceDate)
    }

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

    private fun fetchStockPriceHistoryInCsvStringFromStockCode(stockCode: String): String {
        val doc = WebCrawler.fetchFromUrl(getUrlFromStockCode(stockCode))
        return (doc.childNodes()[0].childNodes()[1].childNodes()[0] as TextNode).text()
    }

    data class StockDetails(val stockCode: String = NOT_APPLICABLE,
                            val latestPriceDate: LocalDate?,
                            val oneYearReturn: String = NOT_APPLICABLE,
                            val oneYearReturnVs200: String = NOT_APPLICABLE,
                            val oneYearReturnVsSector: String = NOT_APPLICABLE)
}
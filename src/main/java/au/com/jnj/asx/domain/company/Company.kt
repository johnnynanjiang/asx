package au.com.jnj.asx.domain.company

import au.com.jnj.asx.domain.sector.NOT_APPLICABLE
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
        val csvRows = removeEmptyRows(csvString.split(" "))
        val latestPriceDate = getLatestPriceDate(csvRows)
        val firstPriceDate = getFirstPriceDate(csvRows)
        return StockDetails(stockCode, latestPriceDate, firstPriceDate)
    }

    internal fun getCsvUrlFromStockCode(stockCode: String): String =
            "https://www.marketindex.com.au/sites/default/files/historical-data/${stockCode.toUpperCase()}.csv"

    private fun removeEmptyRows(csvRows: List<String>): List<String> {
        return csvRows.filter { !it.isNullOrBlank() }
    }

    private fun getLatestPriceDate(csvRows: List<String>): LocalDate? =
            getDateByRowIndex(csvRows, 1)

    private fun getFirstPriceDate(csvRows: List<String>): LocalDate? =
            getDateByRowIndex(csvRows, csvRows.count() - 1)

    private fun getDateByRowIndex(csvRows: List<String>, rowIndex: Int): LocalDate? {
        if (csvRows.count() > 1) {
            val csvRow = csvRows[rowIndex].split(",")

            if (csvRow.toString().isNullOrBlank()) {
                return null
            }

            if (csvRow.count() > 0) {
                return LocalDate.parse(csvRow[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            }
        }

        return null
    }

    private fun fetchStockPriceHistoryInCsvStringFromStockCode(stockCode: String): String {
        val doc = WebCrawler.fetchFromUrl(getCsvUrlFromStockCode(stockCode))
        return (doc.childNodes()[0].childNodes()[1].childNodes()[0] as TextNode).text()
    }

    data class StockDetails(val stockCode: String = NOT_APPLICABLE,
                            val latestPriceDate: LocalDate? = null,
                            val firstPriceDate: LocalDate? = null,
                            val oneYearReturn: String = NOT_APPLICABLE,
                            val oneYearReturnVs200: String = NOT_APPLICABLE,
                            val oneYearReturnVsSector: String = NOT_APPLICABLE)
}
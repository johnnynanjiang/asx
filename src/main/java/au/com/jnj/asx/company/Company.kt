package au.com.jnj.asx.company

import au.com.jnj.asx.sector.NOT_APPLICABLE
import au.com.jnj.asx.util.WebCrawler

/**
 * Created by nanjiang on 22/12/18.
 */

class Company(val name: String = NOT_APPLICABLE, val stockCode: String = NOT_APPLICABLE) {
    fun fetchStockDetailsByStockCode(stockCode: String): StockDetails =
            fetchStockDetailsFromStockCode(stockCode)

    internal fun getUrlFromStockCode(stockCode: String): String {
        return "https://www.marketindex.com.au/asx/${stockCode.toLowerCase()}"
    }

    private fun fetchStockDetailsFromStockCode(stockCode: String): StockDetails {
        val doc = WebCrawler.fetchFromUrl(getUrlFromStockCode(stockCode))
        val oneYearReturn = doc.select("#page > main > div.wrapper > div.individual-companies > div:nth-child(3) > div > div:nth-child(1) > div.hidden-sm > div:nth-child(2) > div:nth-child(1) > div > div > div:nth-child(2) > p > strong > span").toString()
        return StockDetails(stockCode, oneYearReturn)
    }

    data class StockDetails(val stockCode: String = NOT_APPLICABLE,
                            val oneYearReturn: String = NOT_APPLICABLE,
                            val oneYearReturnVs200: String = NOT_APPLICABLE,
                            val oneYearReturnVsSector: String = NOT_APPLICABLE)
}
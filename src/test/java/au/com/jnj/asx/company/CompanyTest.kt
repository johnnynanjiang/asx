package au.com.jnj.asx.company

import org.junit.Assert.*
import org.junit.Test
import java.util.*

/**
 * Created by nanjiang on 22/12/18.
 */
class CompanyTest {
    @Test
    fun testFetchingStockDetailsFromUrl() {
        val company = Company()

        assertEquals("https://www.marketindex.com.au/sites/default/files/historical-data/NDQ.csv", company.getUrlFromStockCode("ndq"))

        val stockDetails = company.fetchStockDetailsByStockCode("ndq")

        assertEquals("2018-11-30", stockDetails.latestPriceDate.toString())
    }

    @Test
    fun testGetLatestPriceDateFromCsv() {
        Company().fetchStockDetailsByStockCode("ndq")
    }
}
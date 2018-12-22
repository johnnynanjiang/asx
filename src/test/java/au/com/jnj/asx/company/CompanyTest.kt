package au.com.jnj.asx.company

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by nanjiang on 22/12/18.
 */
class CompanyTest {
    @Test
    fun testFetchingStockDetailsFromUrl() {
        val company = Company()

        assertEquals("https://www.marketindex.com.au/asx/ndq", company.getUrlFromStockCode("ndq"))

        val stockDetails = company.fetchStockDetailsByStockCode("ndq")

        assertEquals("", stockDetails.oneYearReturn)
    }
}
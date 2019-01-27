package au.com.jnj.asx.company

import au.com.jnj.asx.util.Integration
import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

/**
 * Created by nanjiang on 22/12/18.
 */
class CompanyTest {
    @Test
    @Ignore
    @Integration
    fun testFetchingStockDetailsFromUrl() {
        val company = Company()

        assertEquals("https://www.marketindex.com.au/sites/default/files/historical-data/NDQ.csv", company.getCsvUrlFromStockCode("ndq"))

        val stockDetails = company.fetchStockDetailsByStockCode("ndq")

        assertEquals("2015-05-26", stockDetails.firstPriceDate.toString())
    }

    @Test
    fun testGetPriceDatesFromCsvString() {
        val csvString = "Date,Open,High,Low,Close,Volume 30/11/2018,16.330,16.380,16.320,16.370,55710 29/11/2018,16.380,16.410,16.350,16.360,62966 28/11/2018,16.120,16.120,16.090,16.110,94323 27/11/2018,15.930,15.970,15.890,15.960,49728 26/11/2018,15.630,15.720,15.630,15.720,49822 23/11/2018,15.600,15.650,15.600,15.610,84212 "
        val stockDetails = Company().extractStockDetailsFromCsvString(csvString)

        assertEquals("2018-11-30", stockDetails.latestPriceDate.toString())
        assertEquals("2018-11-23", stockDetails.firstPriceDate.toString())
    }
}
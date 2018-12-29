package au.com.jnj.asx.sector

import au.com.jnj.asx.util.Integration
import au.com.jnj.asx.util.TestUtils
import org.junit.Test
import org.junit.Assert.*
import org.junit.Ignore
import java.io.*
import java.nio.charset.Charset

/**
 * Created by nanjiang on 15/12/18.
 */

class SectorTest {
    @Test
    @Ignore
    @Integration
    fun testFetchingJsonDataFromUrl() {
        val indexJsonDataString = Sector().fetchPerformanceJsonStringFromUrl("https://au.spindices.com/indices/equity/sp-asx-200-utilities-sector")

        assertTrue(indexJsonDataString.startsWith("{\"status\":true,"))
    }

    @Test
    fun testExtractingSectorPerformance() {
        val indexJsonDataString = TestUtils.loadFileToString("index_data_for_industry_sector.json")
        val returns = Sector().extractReturnsFromJsonString(indexJsonDataString)

        assertTrue(returns.oneYearReturn.toString().startsWith("-13.56"))
        assertTrue(returns.threeYearReturn.toString().startsWith("3.55"))
        assertTrue(returns.fiveYearReturn.toString().startsWith("6.67"))
        assertTrue(returns.tenYearReturn.toString().startsWith("6.116"))
    }
}
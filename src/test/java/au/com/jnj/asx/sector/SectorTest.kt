package au.com.jnj.asx.sector

import org.junit.Test
import org.junit.Assert.*
import java.io.*
import java.nio.charset.Charset

/**
 * Created by nanjiang on 15/12/18.
 */

class SectorTest {
    @Test
    fun testGettingJsonDataFromUrl() {
        val indexJsonDataString = Sector().getSectorPerformanceJsonStringFromUrl("https://au.spindices.com/indices/equity/sp-asx-200-utilities-sector")

        assertTrue(indexJsonDataString.startsWith("{\"status\":true,"))
    }

    @Test
    fun testGettingSectorPerformance() {
        val indexJsonDataString = TestUtils.loadFileToString("index_data_for_industry_sector.json")
        val sectorPerformance = Sector().getSectorPerformanceListFromJsonString(indexJsonDataString)

        assertTrue(sectorPerformance.returns.oneYearReturn.toString().startsWith("-13.56"))
        assertTrue(sectorPerformance.returns.threeYearReturn.toString().startsWith("3.55"))
        assertTrue(sectorPerformance.returns.fiveYearReturn.toString().startsWith("6.67"))
        assertTrue(sectorPerformance.returns.tenYearReturn.toString().startsWith("6.116"))
    }
}

class TestUtils {
    companion object {
        fun loadFileToString(filepath: String, charset: Charset = Charsets.ISO_8859_1) : String {
            return File(TestUtils::class.java.classLoader.getResource(filepath).file)
                    .readText(charset)
        }
    }
}
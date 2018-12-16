package au.com.jnj.asx.sector

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by nanjiang on 16/12/18.
 */

class SectorsTest {
    @Test
    fun testGettingPerformanceDetailsList() {
        val performanceDetailsList = Sectors().getPerformanceDetailsForAllSectors()

        assertEquals(15, performanceDetailsList.size)
    }
}
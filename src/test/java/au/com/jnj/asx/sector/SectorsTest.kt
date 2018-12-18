package au.com.jnj.asx.sector

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

/**
 * Created by nanjiang on 16/12/18.
 */

class SectorsTest {
    @Test
    @Ignore
    fun testGettingPerformanceListList() {
        val sectors = Sectors()
        val PerformanceListList = sectors.getPerformanceListForAllSectors()

        assertEquals(15, PerformanceListList.size)
    }

    @Test
    fun testGettingPerformanceListInCSV() {
        val performanceList = listOf(
                SectorPerformance(returns = SectorPerformance.Returns(1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8)),
                SectorPerformance(returns = SectorPerformance.Returns(2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8)),
                SectorPerformance(returns = SectorPerformance.Returns(3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8)))

        Sectors().getPerformanceListInCSV(performanceList)
    }
}
package au.com.jnj.asx.sector

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

/**
 * Created by nanjiang on 16/12/18.
 */

class SectorsTest {
    @Test
    //@Ignore
    fun testGettingPerformanceList() {
        val sectors = Sectors()

        val performanceList = sectors.getPerformanceListForAllSectors()
        sectors.getPerformanceListInCSV(performanceList)

        assertEquals(15, performanceList.size)
    }

    @Test
    fun testGettingPerformanceListInCSV() {
        val performanceList = listOf(
                SectorPerformance(
                        SectorDefinitions.A_REIT,
                        returns = SectorPerformance.Returns(1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8)),
                SectorPerformance(
                        SectorDefinitions.CONSUMER_STAPLES,
                        returns = SectorPerformance.Returns(2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, -2.8)),
                SectorPerformance(
                        SectorDefinitions.COMMUNICATION_SERVICES,
                        returns = SectorPerformance.Returns(2.1, 2.2, 2.3, 2.4, 2.5, 2.66, 2.7, -2.8)),
                SectorPerformance(
                        SectorDefinitions.ENERGY,
                        returns = SectorPerformance.Returns(2.11, 2.2, 2.3, 2.4, 2.5, 2.66, 2.7, -2.8)),
                SectorPerformance(
                        SectorDefinitions.FINANCIAL,
                        returns = SectorPerformance.Returns(3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8)))

        val sectors = Sectors()
        sectors.getPerformanceListInCSV(
                sectors.sortPerformanceList(performanceList)
        )

        assertTrue(performanceList[0].sectorDefinition.equals(SectorDefinitions.FINANCIAL))
        assertTrue(performanceList[1].sectorDefinition.equals(SectorDefinitions.A_REIT))
        assertTrue(performanceList[2].sectorDefinition.equals(SectorDefinitions.ENERGY))
        assertTrue(performanceList[3].sectorDefinition.equals(SectorDefinitions.COMMUNICATION_SERVICES))
        assertTrue(performanceList[4].sectorDefinition.equals(SectorDefinitions.CONSUMER_STAPLES))
    }
}
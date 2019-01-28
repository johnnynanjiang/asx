package au.com.jnj.asx.domain.sector

import au.com.jnj.asx.util.Integration
import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

/**
 * Created by nanjiang on 16/12/18.
 */

class SectorsTest {
    @Test
    @Ignore
    @Integration
    fun testFetchingPerformanceListForAllSectors() {
        val sectors = Sectors()

        val performanceList = sectors.fetchPerformanceListForAllSectors()
        sectors.outputPerformanceListInCsv(performanceList)

        assertEquals(15, performanceList.size)
        assertTrue(performanceList[0].name.equals(SectorDefinitions.HEALTH_CARE))
    }

    @Test
    fun testOutputtingPerformanceListInCsv() {
        val performanceList = listOf(
                SectorPerformance(
                        SectorDefinitions.A_REIT,
                        returns = SectorPerformance.Returns(1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8153)),
                SectorPerformance(
                        SectorDefinitions.CONSUMER_STAPLES,
                        returns = SectorPerformance.Returns(2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, -2.8123)),
                SectorPerformance(
                        SectorDefinitions.COMMUNICATION_SERVICES,
                        returns = SectorPerformance.Returns(2.1, 2.2, 2.3, 2.4, 2.5, 2.66, 2.7, -2.8123)),
                SectorPerformance(
                        SectorDefinitions.ENERGY,
                        returns = SectorPerformance.Returns(2.11, 2.2, 2.3, 2.4, 2.5, 2.66, 2.7, -2.8123)),
                SectorPerformance(
                        SectorDefinitions.FINANCIAL,
                        returns = SectorPerformance.Returns(3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8123)))

        val sectors = Sectors()
        sectors.outputPerformanceListInCsv(
                sectors.sortPerformanceList(performanceList)
        )

        assertTrue(performanceList[0].name == SectorDefinitions.FINANCIAL.description)
        assertTrue(performanceList[1].name == SectorDefinitions.A_REIT.description)
        assertTrue(performanceList[2].name == SectorDefinitions.ENERGY.description)
        assertTrue(performanceList[3].name == SectorDefinitions.COMMUNICATION_SERVICES.description)
        assertTrue(performanceList[4].name == SectorDefinitions.CONSUMER_STAPLES.description)

        assertEquals(1.82, performanceList[1].tenYearReturn, 0.0)
    }
}
package au.com.jnj.asx.sector

/**
 * Created by nanjiang on 16/12/18.
 */

class Sectors {
    fun getPerformanceDetailsForAllSectors() : List<SectorPerformance> {
        val performanceDetailsList: MutableList<SectorPerformance> = mutableListOf()

        for (sectorDefinition in SectorDefinitions.values()) {
            val performanceDetails = Sector().getPerformanceDetailsFromUrl(sectorDefinition.url)
            performanceDetailsList.add(performanceDetails)

            Thread.sleep(1000)
            System.out.println("getPerformanceDetailsForAllSectors() - " + sectorDefinition.description)
        }

        return performanceDetailsList
    }
}
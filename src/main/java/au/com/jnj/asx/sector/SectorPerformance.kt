package au.com.jnj.asx.sector

/**
 * Created by nanjiang on 16/12/18.
 */

data class SectorPerformance(val sectorDefinition: SectorDefinitions,
                             val datetime: Double = 0.0,
                             val returns: Returns) {

    data class Returns(val dailyReturn: Double = 0.0,
                       val monthToDateReturn: Double = 0.0,
                       val quarterToDateReturn: Double = 0.0,
                       val yearToDateReturn: Double = 0.0,
                       val oneYearReturn: Double = 0.0,
                       val threeYearReturn: Double = 0.0,
                       val fiveYearReturn: Double = 0.0,
                       val tenYearReturn: Double = 0.0)
}
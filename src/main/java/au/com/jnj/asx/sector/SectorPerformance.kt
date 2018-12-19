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

    // the following getters are workaround to get CSV BeanWriter to read fields
    val name: String
        get() = this.sectorDefinition.description

    val dailyReturn: Double
        get() = this.returns.dailyReturn

    val monthToDateReturn: Double
        get() = this.returns.monthToDateReturn

    val quarterToDateReturn: Double
        get() = this.returns.quarterToDateReturn

    val yearToDateReturn: Double
        get() = this.returns.yearToDateReturn

    val oneYearReturn: Double
        get() = this.returns.oneYearReturn

    val threeYearReturn: Double
        get() = this.returns.threeYearReturn

    val fiveYearReturn: Double
        get() = this.returns.fiveYearReturn

    val tenYearReturn: Double
        get() = this.returns.tenYearReturn
}
package au.com.jnj.asx.data.model.sector

import java.text.DecimalFormat

/**
 * Created by nanjiang on 16/12/18.
 */

data class SectorPerformance(val sectorDefinition: SectorDefinitions,
                             val datetime: Double = 0.00,
                             val returns: Returns) {

    data class Returns(val dailyReturn: Double = 0.00,
                       val monthToDateReturn: Double = 0.00,
                       val quarterToDateReturn: Double = 0.00,
                       val yearToDateReturn: Double = 0.00,
                       val oneYearReturn: Double = 0.00,
                       val threeYearReturn: Double = 0.00,
                       val fiveYearReturn: Double = 0.00,
                       val tenYearReturn: Double = 0.00)

    private fun formatDouble(double: Double): Double =
            DecimalFormat("#.##").format(double).toDouble()

    // the following getters are a workaround to get Csv BeanWriter to read fields
    val name: String
        get() = this.sectorDefinition.description

    val dailyReturn: Double
        get() = formatDouble(this.returns.dailyReturn)

    val monthToDateReturn: Double
        get() = formatDouble(this.returns.monthToDateReturn)

    val quarterToDateReturn: Double
        get() = formatDouble(this.returns.quarterToDateReturn)

    val yearToDateReturn: Double
        get() = formatDouble(this.returns.yearToDateReturn)

    val oneYearReturn: Double
        get() = formatDouble(this.returns.oneYearReturn)

    val threeYearReturn: Double
        get() = formatDouble(this.returns.threeYearReturn)

    val fiveYearReturn: Double
        get() = formatDouble(this.returns.fiveYearReturn)

    val tenYearReturn: Double
        get() = formatDouble(this.returns.tenYearReturn)
}
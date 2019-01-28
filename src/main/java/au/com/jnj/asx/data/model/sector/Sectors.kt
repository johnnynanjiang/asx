package au.com.jnj.asx.data.model.sector

import org.supercsv.io.CsvBeanWriter
import org.supercsv.prefs.CsvPreference
import java.io.PrintWriter
import java.util.*

/**
 * Created by nanjiang on 16/12/18.
 */

class Sectors {
    fun fetchPerformanceListForAllSectors(): List<SectorPerformance> {
        var performanceList: MutableList<SectorPerformance> = mutableListOf()

        for (sectorDefinition in SectorDefinitions.values()) {
            val performance = Sector().getPerformanceForSector(sectorDefinition)
            performanceList.add(performance)

            System.out.println("fetchPerformanceListForAllSectors() - " + sectorDefinition.description)
        }

        return sortPerformanceList(performanceList)
    }

    // ToDo: apply Functional Programming with immutable list
    internal fun sortPerformanceList(performanceList: List<SectorPerformance>): List<SectorPerformance> {
        Collections.sort(
                performanceList,
                fun(performanceA, performanceB): Int {
                    var compareResult = performanceB.returns.tenYearReturn.compareTo(
                            performanceA.returns.tenYearReturn)
                    if (compareResult != 0) {
                        return compareResult
                    }

                    compareResult = performanceB.returns.fiveYearReturn.compareTo(
                            performanceA.returns.fiveYearReturn)

                    if (compareResult != 0) {
                        return compareResult
                    }

                    compareResult = performanceB.returns.threeYearReturn.compareTo(
                            performanceA.returns.threeYearReturn)

                    if (compareResult != 0) {
                        return compareResult
                    }

                    compareResult = performanceB.returns.oneYearReturn.compareTo(
                            performanceA.returns.oneYearReturn)

                    if (compareResult != 0) {
                        return compareResult
                    }

                    compareResult = performanceB.returns.yearToDateReturn.compareTo(
                            performanceA.returns.yearToDateReturn)

                    if (compareResult != 0) {
                        return compareResult
                    }

                    compareResult = performanceB.returns.quarterToDateReturn.compareTo(
                            performanceA.returns.quarterToDateReturn)

                    if (compareResult != 0) {
                        return compareResult
                    }

                    compareResult = performanceB.returns.monthToDateReturn.compareTo(
                            performanceA.returns.monthToDateReturn)

                    if (compareResult != 0) {
                        return compareResult
                    }

                    return performanceB.returns.dailyReturn.compareTo(performanceA.returns.dailyReturn)
                }
        )

        return performanceList
    }

    internal fun outputPerformanceListInCsv(performanceList: List<SectorPerformance>) {
        val beanWriter = CsvBeanWriter(PrintWriter(System.out), CsvPreference.STANDARD_PREFERENCE)
        val headers = arrayOf(
                "name",
                "dailyReturn",
                "monthToDateReturn",
                "quarterToDateReturn",
                "yearToDateReturn",
                "oneYearReturn",
                "threeYearReturn",
                "fiveYearReturn",
                "tenYearReturn")

        try {
            beanWriter.writeHeader(*headers)
            for (performance in performanceList) {
                beanWriter.write(performance, *headers)
            }
        } finally {
            // ToDo: comment it out as it breaks the unit test for some reason
            // beanWriter.close()
        }
    }
}
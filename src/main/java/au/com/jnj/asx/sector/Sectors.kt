package au.com.jnj.asx.sector

import org.supercsv.io.CsvBeanWriter
import org.supercsv.prefs.CsvPreference
import java.io.PrintWriter

/**
 * Created by nanjiang on 16/12/18.
 */

class Sectors {
    private var _performanceList: List<SectorPerformance>? = null

    val performanceList: List<SectorPerformance>
        @Synchronized get() {
            if (_performanceList == null) {
                _performanceList = getPerformanceListForAllSectors()
            }

            return _performanceList!!
        }

    fun getPerformanceListForAllSectors() : List<SectorPerformance> {
        val PerformanceListList: MutableList<SectorPerformance> = mutableListOf()

        for (sectorDefinition in SectorDefinitions.values()) {
            val performanceList = Sector().getPerformanceListFromUrl(sectorDefinition.url)
            PerformanceListList.add(performanceList)

            Thread.sleep(1000)
            System.out.println("getPerformanceListForAllSectors() - " + sectorDefinition.description)
        }

        return PerformanceListList
    }

    internal fun sortPerformanceList() : List<SectorPerformance> {
        //Collections.sort(this.performanceList, )
        return performanceList
    }

    internal fun getPerformanceListInCSV(performanceList: List<SectorPerformance>) {
        val beanWriter = CsvBeanWriter(PrintWriter(System.out), CsvPreference.STANDARD_PREFERENCE)
        /*
        val header = arrayOf(
                "Daily Return",
                "Monthly Return",
                "Quarterly Return",
                "Yearly Return",
                "1 Year Return",
                "3 Years Return",
                "5 Years Return",
                "10 Years Return")
        */
        val headers = arrayOf(
                "dailyReturn",
                "monthToDateReturn",
                "quarterToDateReturn",
                "yearToDateReturn",
                "oneYearReturn",
                "threeYearReturn",
                "fiveYearReturn",
                "tenYearReturn")
        beanWriter.writeHeader(*headers)
        for (performanceDetail in performanceList) {
            beanWriter.write(performanceDetail.returns, *headers)
        }

        beanWriter.flush()
        beanWriter.close()
    }
}
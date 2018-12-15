package au.com.jnj.asx.sector

import org.jsoup.Jsoup
import org.junit.Test
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.junit.Assert.*

/**
 * Created by nanjiang on 15/12/18.
 */

class SectorTest {
    @Test
    fun testGettingSectorPerformance() {
        val doc: Document = Jsoup.connect("https://au.spindices.com/indices/equity/sp-asx-200-utilities-sector").timeout(10 * 1000).get()
        val bodyTag = doc.getElementsByTag("body")[0] // TODO: get index data from body text directly for fault tolerance
        val scriptTags = doc.getElementsByTag("script")
        val scriptTagWithData = scriptTags[11]
        val scriptCodeBlock = scriptTagWithData.childNodes()[0].attributes().get("data")
        val indexDataText = scriptCodeBlock.substringAfter("var indexData = ").substringBefore(";")

        assertEquals(23, scriptTags.size)
        assertTrue(indexDataText.startsWith("{\"status\":true,"))
    }
}
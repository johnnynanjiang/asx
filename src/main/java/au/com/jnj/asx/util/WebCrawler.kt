package au.com.jnj.asx.util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by nanjiang on 22/12/18.
 */

class WebCrawler {
    companion object {
        private const val TIMEOUT = 10 * 1000
        private const val USER_AGENT = "Mozilla"

        fun fetchFromUrl(url: String): Document =
                Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIMEOUT).get()
    }
}
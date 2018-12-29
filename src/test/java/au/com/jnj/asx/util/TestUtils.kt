package au.com.jnj.asx.util

import java.io.File
import java.nio.charset.Charset

/**
 * Created by nanjiang on 30/12/18.
 */

class TestUtils {
    companion object {
        fun loadFileToString(filepath: String, charset: Charset = Charsets.ISO_8859_1) : String {
            return File(TestUtils::class.java.classLoader.getResource(filepath).file)
                    .readText(charset)
        }
    }
}

annotation class Integration
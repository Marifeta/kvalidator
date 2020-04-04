package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UrlTest : LibraryTest() {
    private val testData = data.getObject("web_types")

    @Test
    fun testValidUrl() {
        for (url in testData.getArray("url")) {
            val testJson = JsonObject(mapOf("url" to url))
            val rule = mapOf<String, List<Rule>>("url" to listOf(Url()))

            assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["url"]} should return true""")
        }
    }

    @Test
    fun testInvalidUrl() {
        val testJson = JsonObject(
            mapOf(
                "a1" to JsonPrimitive("2"),
                "a2" to JsonPrimitive("test.test"),
                "a3" to JsonPrimitive(true)
            )
        )

        for (item in testJson) {
            val rule = mapOf<String, List<Rule>>(item.key to listOf(Url()))
            assertFalse(Validator(testJson, rule).validate(), """result for "${item.value}" should return false""")
        }
    }
}
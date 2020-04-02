package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestRulesWebTypes : LibraryTest() {
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

    @Test
    fun testValidEmail() {
        for (email in testData.getArray("email")) {
            val testJson = JsonObject(mapOf("email" to email))
            val rule = mapOf<String, List<Rule>>("email" to listOf(Email()))
            assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["email"]} should return true""")
        }
    }

    @Test
    fun testInvalidEmail() {
        val testJson = JsonObject(
            mapOf(
                "m1" to JsonPrimitive(23),
                "m2" to JsonPrimitive("test.test@domain"),
                "m3" to JsonPrimitive("test@domain."),
                "m4" to JsonPrimitive("domain_d.ru"),
                "m5" to JsonPrimitive(true)
            )
        )

        for (item in testJson) {
            val rule = mapOf<String, List<Rule>>(item.key to listOf(Email()))
            assertFalse(Validator(testJson, rule).validate(), """result for "${item.value}" should return false""")
        }
    }
}

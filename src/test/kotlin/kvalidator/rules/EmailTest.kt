package kvalidator.rules

import kotlinx.serialization.json.*
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kvalidator.LibraryTest
import kvalidator.Validator

class EmailTest : LibraryTest() {
    private val testData = data.getValue("web_types").jsonObject

    @Test
    fun testValidEmail() {
        for (email in testData.getValue("email").jsonArray) {
            val testJson = JsonObject(mapOf("email" to email))
            val rule = mapOf<String, List<Rule>>(
                "email" to listOf(
                    Email()
                )
            )

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
            val rule = mapOf<String, List<Rule>>(
                item.key to listOf(
                    Email()
                )
            )
            assertFalse(Validator(testJson, rule).validate(), """result for "${item.value}" should return false""")
        }
    }
}

package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.Test

class ConfirmedTest : LibraryTest() {
    private val testData = data.getObject("field_properties")
    private val invalidData = data.getObject("invalid_data")

    @Test
    fun testConfirmedField() {
        val testJson = testData.getObject("confirmed")
        val rule = mapOf<String, List<Rule>>("field" to listOf(Confirmed()))
        assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return true""")
    }

    @Test
    fun testUnConfirmedField() {
        for (data in testData.getArray("confirmed_false")) {
            val testJson = JsonObject(mapOf("field" to data))
            val rule = mapOf<String, List<Rule>>("field" to listOf(Confirmed()))
            assertFalse(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return false""")
        }
    }
}
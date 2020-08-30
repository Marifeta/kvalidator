package kvalidator.rules

import kotlinx.serialization.json.*
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.Test

class ConfirmedTest : LibraryTest() {
    private val testData = data.getValue("field_properties").jsonObject
    private val invalidData = data.getValue("invalid_data").jsonObject

    @Test
    fun testConfirmedField() {
        val testJson = testData.getValue("confirmed").jsonObject
        val rule = mapOf<String, List<Rule>>("field" to listOf(Confirmed()))
        assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return true""")
    }

    @Test
    fun testUnConfirmedField() {
        for (data in testData.getValue("confirmed_false").jsonArray) {
            val testJson = JsonObject(mapOf("field" to data))
            val rule = mapOf<String, List<Rule>>("field" to listOf(Confirmed()))
            assertFalse(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return false""")
        }
    }
}
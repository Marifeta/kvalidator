package kvalidator.rules

import kotlinx.serialization.json.*
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.Test

class RequiredTest : LibraryTest() {
    private val testData = data.getValue("field_properties").jsonObject
    private val invalidData = data.getValue("invalid_data").jsonObject

    @Test
    fun testRequiredFieldExist() {
        for (data in testData.getValue("required").jsonArray) {
            val testJson = JsonObject(mapOf("field" to data))
            val rule = mapOf<String, List<Rule>>("field" to listOf(Required()))
            assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return true""")
        }
    }

    @Test
    fun testRequiredFieldEmpty() {
        for (data in testData.getValue("required_empty").jsonArray) {
            val testJson = JsonObject(mapOf("field" to data))
            val rule = mapOf<String, List<Rule>>("field" to listOf(Required()))
            assertFalse(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return false""")
        }
    }
}
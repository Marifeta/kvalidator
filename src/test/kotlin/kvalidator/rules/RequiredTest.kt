package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.Test

class RequiredTest : LibraryTest() {
    private val testData = data.getObject("field_properties")
    private val invalidData = data.getObject("invalid_data")

    @Test
    fun testRequiredFieldExist() {
        for (data in testData.getArray("required")) {
            val testJson = JsonObject(mapOf("field" to data))
            val rule = mapOf<String, List<Rule>>("field" to listOf(Required()))
            assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return true""")
        }
    }

    @Test
    fun testRequiredFieldEmpty() {
        for (data in testData.getArray("required_empty")) {
            val testJson = JsonObject(mapOf("field" to data))
            val rule = mapOf<String, List<Rule>>("field" to listOf(Required()))
            assertFalse(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return false""")
        }
    }
}
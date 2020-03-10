package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.Test

class TestRulesFieldProperties : LibraryTest() {
    private val testData = data.getObject("field_properties")

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

    @Test
    fun testAcceptedField() {
        for (data in testData.getArray("accepted")) {
            val testJson = JsonObject(mapOf("field" to data))
            val rule = mapOf<String, List<Rule>>("field" to listOf(Accepted()))
            assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return true""")
        }
    }

    @Test
    fun testAcceptedFieldFalse() {
        for (data in testData.getArray("accepted_false")) {
            val testJson = JsonObject(mapOf("field" to data))
            val rule = mapOf<String, List<Rule>>("field" to listOf(Accepted()))
            assertFalse(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return false""")
        }
    }
}

package kvalidator.rules

import kotlinx.serialization.json.*
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.Test

class AcceptedTest : LibraryTest() {
    private val testData = data.getValue("field_properties").jsonObject
    private val invalidData = data.getValue("invalid_data").jsonObject

    @Test
    fun testAcceptedField() {
        for (data in testData.getValue("accepted").jsonArray) {
            val testJson = JsonObject(mapOf("field" to data))
            val rule = mapOf<String, List<Rule>>("field" to listOf(Accepted()))
            assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return true""")
        }
    }

    @Test
    fun testAcceptedFieldFalse() {
        for (data in testData.getValue("accepted_false").jsonArray) {
            val testJson = JsonObject(mapOf("field" to data))
            val rule = mapOf<String, List<Rule>>("field" to listOf(Accepted()))
            assertFalse(Validator(testJson, rule).validate(), """result for ${testJson["field"]} should return false""")
        }
    }
}

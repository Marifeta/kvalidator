package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.Test

class AcceptedTest : LibraryTest() {
    private val testData = data.getObject("field_properties")
    private val invalidData = data.getObject("invalid_data")

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

package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kvalidator.LibraryTest
import kvalidator.Validator
import kvalidator.i18n.en
import kvalidator.i18n.ru
import kvalidator.parseRules
import kvalidator.stringifyRules
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.Test
import kotlin.test.assertEquals

class TestRulesFieldProperties : LibraryTest() {
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

    @Test
    fun testInvalidateRulesHasCorrectMessage() {
        val rules = mapOf<String, List<Rule>>(
                "short_5_email" to listOf(
                        Required(),
                        Email(),
                        Max(15),
                        Min(8),
                        Size(8),
                        IsBoolean()
                )
        )

        val validator = Validator(invalidData, rules, lang = en)
        assertFalse(validator.validate())

        validator.errors.forEach { (attrName, errors) ->
            assertEquals(errors[0], "The short_5_email must be at least 8 characters.")
            assertEquals(errors[1], "The short_5_email must be 8 characters.")
            assertEquals(errors[2], "The short_5_email field must be true or false.")
        }
    }

    @Test
    fun parseAndStringify() {
        val rules = listOf(
                Required(),
                Email(),
                Max(15),
                Min(8),
                Size(8),
                IsBoolean()
        )

        assertEquals("required|email|max:15|min:8|size:8|boolean", stringifyRules(rules))
        val parsed = parseRules("required|email|max:15|min:8|size:8|boolean")
        val required = parsed[0] as Required
        val email = parsed[1] as Email
        val max = parsed[2] as Max
        val min = parsed[3] as Min
        val size = parsed[4] as Size
        val boolean = parsed[5] as IsBoolean

        assertEquals(max.value, 15)
        assertEquals(min.value, 8)
        assertEquals(size.value, 8)
    }
}

package kvalidator.rules

import kotlinx.serialization.json.getObject
import kotlinx.serialization.json.jsonObject
import kvalidator.LibraryTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kvalidator.Validator

class BetweenTest : LibraryTest() {
    private val testData = data.getValue("value_properties").jsonObject
    private val testJsonBetween = testData.getValue("between").jsonObject

    @Test
    fun testBetweenValue() {
        val rules = mapOf<String, List<Rule>>(
            "one_number" to listOf(Between(5, 30)),
            "one_string" to listOf(Between(3, 7))
        )
        assertTrue(Validator(testJsonBetween, rules).validate(), "result should return true")
    }

    @Test
    fun testBetweenValueFalse() {
        val testJson = testData.getValue("between").jsonObject
        val rules = mapOf<String, List<Rule>>(
            "one_number" to listOf(Between(1, 3)),
            "one_string" to listOf(Between(6, 14))
        )

        assertFalse(Validator(testJson, rules).validate(), "result should return false")
    }
}
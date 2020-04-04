package kvalidator.rules

import kvalidator.LibraryTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kvalidator.Validator

class BetweenTest : LibraryTest() {
    private val testData = data.getObject("value_properties")
    private val testJsonBetween = testData.getObject("between")

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
        val testJson = testData.getObject("between")
        val rules = mapOf<String, List<Rule>>(
            "one_number" to listOf(Between(1, 3)),
            "one_string" to listOf(Between(6, 14))
        )

        assertFalse(Validator(testJson, rules).validate(), "result should return false")
    }
}
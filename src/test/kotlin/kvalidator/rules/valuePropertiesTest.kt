package kvalidator.rules

import kvalidator.LibraryTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kvalidator.Validator

class TestRulesValueProperties : LibraryTest() {
    private val testData = data.getObject("value_properties")
    private val testJsonSize = testData.getObject("size")
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

    @Test
    fun testSizeValue() {
        val rules = mapOf<String, List<Rule>>(
            "v1" to listOf(Size(-10)),
            "v2" to listOf(Size(3)),
            "v3" to listOf(Size(7))
        )
        assertTrue(Validator(testJsonSize, rules).validate(), "result should return true")
    }

    @Test
    fun testSizeValueFalse() {
        val rules = mapOf<String, List<Rule>>(
            "v1" to listOf(Size(5)),
            "v2" to listOf(Size(2)),
            "v3" to listOf(Size(8))
        )
        assertFalse(Validator(testJsonSize, rules).validate(), "result should return false")
    }

    @Test
    fun testMinValue() {
        val rules = mapOf<String, List<Rule>>(
            "v1" to listOf(Min(-15)),
            "v2" to listOf(Min(2)),
            "v3" to listOf(Min(6))
        )
        assertTrue(Validator(testJsonSize, rules).validate(), "result should return true")
    }

    @Test
    fun testMinValueFalse() {
        val rules = mapOf<String, List<Rule>>(
            "v1" to listOf(Min(5)),
            "v2" to listOf(Min(4)),
            "v3" to listOf(Min(8))
        )
        assertFalse(Validator(testJsonSize, rules).validate(), "result should return false")
    }

    @Test
    fun testMaxValue() {
        val rules = mapOf<String, List<Rule>>(
            "v1" to listOf(Max(-10)),
            "v2" to listOf(Max(3)),
            "v3" to listOf(Max(8))
        )
        assertTrue(Validator(testJsonSize, rules).validate(), "result should return true")
    }

    @Test
    fun testMaxValueFalse() {
        val rules = mapOf<String, List<Rule>>(
            "v1" to listOf(Max(-20)),
            "v2" to listOf(Max(2)),
            "v3" to listOf(Max(4))
        )
        assertFalse(Validator(testJsonSize, rules).validate(), "result should return false")
    }
}
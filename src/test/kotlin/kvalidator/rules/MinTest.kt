package kvalidator.rules

import kvalidator.LibraryTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kvalidator.Validator

class MinTest : LibraryTest() {
    private val testData = data.getObject("value_properties")
    private val testJsonSize = testData.getObject("size")

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
}
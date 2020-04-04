package kvalidator.rules

import kvalidator.LibraryTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kvalidator.Validator

class SizeTest : LibraryTest() {
    private val testData = data.getObject("value_properties")
    private val testJsonSize = testData.getObject("size")

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
}
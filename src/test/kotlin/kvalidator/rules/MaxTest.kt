package kvalidator.rules

import kotlinx.serialization.json.getObject
import kotlinx.serialization.json.jsonObject
import kvalidator.LibraryTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kvalidator.Validator

class MaxTest : LibraryTest() {
    private val testData = data.getValue("value_properties").jsonObject
    private val testJsonSize = testData.getValue("size").jsonObject

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
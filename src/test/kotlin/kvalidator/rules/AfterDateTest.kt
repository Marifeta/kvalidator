package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.Test

class AfterDateTest : LibraryTest() {
    @Test
    fun testAfterDate() {
        val minDate = "2000-12-01T00:00:05"
        val maxDate = "2000-12-01T00:00:45"

        // valid
        var testJson = JsonObject(mapOf("date" to JsonPrimitive(maxDate)))
        var rule = mapOf<String, List<Rule>>("date" to listOf(AfterDate(minDate)))
        assertTrue(
            Validator(testJson, rule).validate(),
            """result for $minDate > ${testJson["date"]} should return true"""
        )

        // invalid
        testJson = JsonObject(mapOf("date" to JsonPrimitive(minDate)))
        rule = mapOf<String, List<Rule>>("date" to listOf(AfterDate(maxDate)))
        assertFalse(
            Validator(testJson, rule).validate(),
            """result for $minDate > ${testJson["date"]} should return false"""
        )
    }
}
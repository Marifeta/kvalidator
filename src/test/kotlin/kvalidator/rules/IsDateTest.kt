package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.assertTrue
import kotlin.test.Test

class IsDateTest : LibraryTest() {
    private val testData = data.getObject("date_types")

    @Test
    fun testValidDate() {
        for (date in testData.getArray("date")) {
            val testJson = JsonObject(mapOf("date" to date))
            val rule = mapOf<String, List<Rule>>("date" to listOf(IsDate()))
            assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["date"]} should return true""")
        }
    }
}
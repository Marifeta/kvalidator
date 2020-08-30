package kvalidator.rules

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.*
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.Test

class IsDateTest : LibraryTest() {
    private val testData = data.getValue("date_types").jsonObject

    @Test
    fun testKotlinxDateTimeParse() {
        val r = try {
            "2020-06-01T22:18:01.475Z".toInstant()
            "2020-06-01T22:19:40".toLocalDateTime()
            Instant.parse("2010-06-01T22:19:42.475Z")
            LocalDateTime.parse("2010-06-01T22:19:43")
            true
        } catch (e: IllegalArgumentException) {
            false
        }
        assertTrue(r)
    }

    @Test
    fun testValidDate() {
        for (date in testData.getValue("date").jsonArray) {
            val testJson = JsonObject(mapOf("date" to date))
            val rule = mapOf<String, List<Rule>>("date" to listOf(IsDate()))
            assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["date"]} should return true""")
        }
    }

    @Test
    fun testInvalidDate() {
        val testJson = JsonObject(mapOf("date" to JsonPrimitive("fri 21 october")))
        val rule = mapOf<String, List<Rule>>("date" to listOf(IsDate()))
        assertFalse(Validator(testJson, rule).validate(), """result for ${testJson["date"]} should return false""")
    }
}
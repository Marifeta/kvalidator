package kvalidator.rules

import kvalidator.LibraryTest
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kvalidator.Validator
import kotlin.test.Test
import kotlin.test.assertTrue

class AlphaNumTest : LibraryTest() {
    @Test
    fun testValidAlphaNum() {
        val testJson = JsonObject(mapOf("alpha" to JsonPrimitive("AAbbD3DD11cc")))
        val rule = mapOf<String, List<Rule>>("alpha" to listOf(AlphaNum()))
        assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["alpha"]} should return true""")
    }
}
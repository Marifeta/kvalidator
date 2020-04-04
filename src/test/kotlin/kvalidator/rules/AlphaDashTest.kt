package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kvalidator.LibraryTest
import kvalidator.Validator
import kotlin.test.Test
import kotlin.test.assertTrue

class AlphaDashTest : LibraryTest() {
    @Test
    fun testValidAlphaDash() {
        val testJson = JsonObject(mapOf("alpha" to JsonPrimitive("AAbb-D3DD11_cc")))
        val rule = mapOf<String, List<Rule>>("alpha" to listOf(AlphaDash()))
        assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["alpha"]} should return true""")
    }
}
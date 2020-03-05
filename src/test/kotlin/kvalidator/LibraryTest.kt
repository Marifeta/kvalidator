package kvalidator

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.serialization.json.*
import kotlinx.serialization.json.Json
import kvalidator.i18n.ru
import kvalidator.rules.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

open class LibraryTest {
    private val json = Json(JsonConfiguration.Stable)
    internal val data = this::class.java.getResource("/data.json").readText().let { json.parseJson(it) }.jsonObject
}

@RunWith(Parameterized::class)
class TestRulesBasicTypes(private val ruleClass: Rule, private val keyNames: List<String>) : LibraryTest() {
    private val testData = data.getObject("basic_types")

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf(IsInteger(), listOf("int", "int_min", "zero", "int_string", "int_string_min", "float_digit")),
                arrayOf(IsString(), listOf("str", "str_empty", "int_string", "int_string_min")),
                arrayOf(IsBoolean(), listOf("bool")),
                arrayOf(IsArray(), listOf("list", "dict")),
                arrayOf(IsNumeric(), listOf("int", "int_min", "float", "float_min", "zero", "float_digit"))
            )
        }
    }

    @Test
    fun testValidTypes() {
        for (keyName in keyNames) {
            val rules = mapOf(keyName to listOf(ruleClass))
            assertTrue(Validator(testData, rules).validate(), """result for "$keyName" should return true""")
        }
    }

    @Test
    fun testInvalidTypes() {
        for (item in testData) {
            if (item.key !in keyNames) {
                val rules = mapOf(item.key to listOf(ruleClass))
                assertFalse(Validator(testData, rules).validate(), """result for "${item.key}" should return false""")
            }
        }
    }

    @Test
    fun testNonexistentKey() {
        val rules = mapOf("nonexistent" to listOf(ruleClass))
        assertTrue(Validator(testData, rules).validate(), "result should return true")
    }
}

class TestRulesWebTypes : LibraryTest() {
    private val testData = data.getObject("web_types")

    @Test
    fun testValidUrl() {
        for (url in testData.getArray("url")) {
            val testJson = JsonObject(mapOf("url" to url))
            val rule = mapOf<String, List<Rule>>("url" to listOf(Url()))

            assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["url"]} should return true""")
        }
    }

    @Test
    fun testInvalidUrl() {
        val testJson = JsonObject(
            mapOf(
                "a1" to JsonPrimitive("2"),
                "a2" to JsonPrimitive("test.test"),
                "a3" to JsonPrimitive(true)
            )
        )

        for (item in testJson) {
            val rule = mapOf<String, List<Rule>>(item.key to listOf(Url()))
            assertFalse(Validator(testJson, rule).validate(), """result for "${item.value}" should return false""")
        }
    }

    @Test
    fun testValidEmail() {
        for (email in testData.getArray("email")) {
            val testJson = JsonObject(mapOf("email" to email))
            val rule = mapOf<String, List<Rule>>("email" to listOf(Email()))
            assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["email"]} should return true""")
        }
    }

    @Test
    fun testInvalidEmail() {
        val testJson = JsonObject(
            mapOf(
                "m1" to JsonPrimitive(23),
                "m2" to JsonPrimitive("test.test@domain"),
                "m3" to JsonPrimitive("test@domain.d"),
                "m4" to JsonPrimitive("domain_d.ru"),
                "m5" to JsonPrimitive(true)
            )
        )

        for (item in testJson) {
            val rule = mapOf<String, List<Rule>>(item.key to listOf(Email()))
            assertFalse(Validator(testJson, rule).validate(), """result for "${item.value}" should return false""")
        }
    }
}

class TestRulesDateTypes : LibraryTest() {
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

class TestRulesAlphabeticTypes : LibraryTest() {
    @Test
    fun testValidAlpha() {
        val testJson = JsonObject(mapOf("alpha" to JsonPrimitive("AAbbDDDcc")))
        val rule = mapOf<String, List<Rule>>("alpha" to listOf(Alpha()))
        assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["alpha"]} should return true""")
    }

    @Test
    fun testValidAlphaNum() {
        val testJson = JsonObject(mapOf("alpha" to JsonPrimitive("AAbbD3DD11cc")))
        val rule = mapOf<String, List<Rule>>("alpha" to listOf(AlphaNum()))
        assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["alpha"]} should return true""")
    }

    @Test
    fun testValidAlphaDash() {
        val testJson = JsonObject(mapOf("alpha" to JsonPrimitive("AAbb-D3DD11_cc")))
        val rule = mapOf<String, List<Rule>>("alpha" to listOf(AlphaDash()))
        assertTrue(Validator(testJson, rule).validate(), """result for ${testJson["alpha"]} should return true""")
    }
}

class TestRuleFieldProperties : LibraryTest() {

}

class LibraryTestOld {
    private val json = Json(JsonConfiguration.Stable)
    private val data = this::class.java.getResource("/data.json").readText().let { json.parseJson(it) }.jsonObject

    @Test
    fun sizeNotEqualToUserNumber() {
        val rules = mapOf<String, List<Rule>>("age" to listOf(Size(-10)))
        assertTrue(Validator(data["sizeNotEqualToUserNumber"]!!, rules, ru).validate(), "result should return true")
    }

    @Test
    fun betweenRuleValid() {
        val rules = mapOf<String, List<Rule>>(
            "one_number" to listOf(Between(5, 30)),
            "one_string" to listOf(Between(3, 7))
        )
        assertTrue(Validator(data["between_data"]!!, rules, ru).validate(), "result should return true")
    }

    @Test
    fun betweenRuleNotValid() {
        val rules = mapOf<String, List<Rule>>(
            "one_number" to listOf(Between(1, 3)),
            "one_string" to listOf(Between(6, 14))
        )

        assertFalse(Validator(data["between_data"]!!, rules, ru).validate(), "result should return false")
    }
}


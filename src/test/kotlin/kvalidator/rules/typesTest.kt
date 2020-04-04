package kvalidator.rules

import kvalidator.LibraryTest
import kvalidator.Validator
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.Test

@RunWith(Parameterized::class)
class TestTypes(private val ruleClass: Rule, private val keyNames: List<String>) : LibraryTest() {
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

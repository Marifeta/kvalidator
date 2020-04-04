package kvalidator

import kvalidator.rules.Email
import kvalidator.rules.IsBoolean
import kvalidator.rules.Max
import kvalidator.rules.Min
import kvalidator.rules.Required
import kvalidator.rules.Size
import kotlin.test.Test
import kotlin.test.assertEquals

class StringifyRulesTest : LibraryTest() {
    @Test
    fun parseAndStringify() {
        val rules = listOf(
            Required(),
            Email(),
            Max(15),
            Min(8),
            Size(8),
            IsBoolean()
        )

        assertEquals("required|email|max:15|min:8|size:8|boolean", stringifyRules(rules))
        val parsed = parseRules("required|email|max:15|min:8|size:8|boolean")
        val required = parsed[0] as Required
        val email = parsed[1] as Email
        val max = parsed[2] as Max
        val min = parsed[3] as Min
        val size = parsed[4] as Size
        val boolean = parsed[5] as IsBoolean

        assertEquals(max.value, 15)
        assertEquals(min.value, 8)
        assertEquals(size.value, 8)
    }
}
package kvalidator.rules

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject

class IsNumeric : Rule() {
    override val name = "numeric"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonLiteral -> {
                when {
                    element.isString -> false
                    element.doubleOrNull != null -> true
                    else -> false
                }
            }
            else -> false
        }
    }
}
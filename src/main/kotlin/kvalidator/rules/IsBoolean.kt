package kvalidator.rules

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject

class IsBoolean : Rule() {
    override val name: String = "boolean"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonLiteral -> {
                when {
                    element.isString -> false
                    element.booleanOrNull != null -> true
                    else -> false
                }
            }
            else -> false
        }
    }
}
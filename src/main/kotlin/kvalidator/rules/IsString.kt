package kvalidator.rules

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

class IsString : Rule() {
    override val name = "string"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonPrimitive -> {
                when {
                    element.isString -> true
                    else -> false
                }
            }
            else -> false
        }
    }
}
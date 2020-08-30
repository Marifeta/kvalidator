package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull

class IsBoolean : Rule() {
    override val name: String = "boolean"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonPrimitive -> {
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
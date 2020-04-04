package kvalidator.rules

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject

class Required : Rule() {
    override val name: String = "required"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return false
        if (!data.containsKey(attribute)) return false

        return when (val element = data[attribute]) {
            is JsonNull -> false
            is JsonLiteral -> {
                when {
                    element.isString -> element.content.length.toDouble() > 0
                    else -> true
                }
            }
            is JsonArray -> {
                when {
                    element.isNotEmpty() -> true
                    else -> false
                }
            }
            is JsonObject -> {
                when {
                    element.isNotEmpty() -> true
                    else -> false
                }
            }
            else -> false
        }
    }
}
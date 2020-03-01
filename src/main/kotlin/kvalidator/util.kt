package kvalidator

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonArray

fun getSize(value: JsonElement?): Double? {
    return when (value) {
        is JsonLiteral -> {
            when {
                value.isString -> value.content.length.toDouble()
                value.doubleOrNull != null -> value.double
                value.booleanOrNull != null || value.isNull -> null
                else -> null
            }
        }
        is JsonObject -> value.size.toDouble()
        is JsonArray -> value.size.toDouble()
        else -> null
    }
}
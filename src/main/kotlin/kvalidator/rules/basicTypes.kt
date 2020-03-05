package kvalidator.rules

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject

class IsInteger : Rule() {
    override val name = "integer"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonLiteral -> when {
                element.floatOrNull != null -> {
                    val converted = element.float
                    return (converted.compareTo(converted.toInt()) == 0)
                }
                element.intOrNull != null -> true
                else -> false
            }
            else -> false
        }
    }
}

class IsString : Rule() {
    override val name = "string"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonLiteral -> {
                when {
                    element.isString -> true
                    else -> false
                }
            }
            else -> false
        }
    }
}

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

class IsArray : Rule() {
    override val name: String = "array"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (data[attribute]) {
            is JsonArray -> true
            is JsonObject -> true
            else -> false
        }
    }
}

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
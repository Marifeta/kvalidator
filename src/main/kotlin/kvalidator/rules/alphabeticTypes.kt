package kvalidator.rules

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject

class Alpha : Rule() {
    override val name: String = "alpha"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonLiteral -> {
                when {
                    element.booleanOrNull != null -> false
                    element.isString -> {
                        val regex = Regex(pattern = """[A-Za-zА-Яа-яёЁ]+$""")
                        return regex.matches(input = element.content)
                    }
                    else -> false
                }
            }
            else -> false
        }
    }
}

class AlphaNum : Rule() {
    override val name: String = "alpha_num"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonLiteral -> {
                when {
                    element.booleanOrNull != null -> false
                    element.isString || element.doubleOrNull != null -> {
                        val regex = Regex(pattern = """[A-Za-zА-Яа-яёЁ0-9]+$""")
                        return regex.matches(input = element.content)
                    }
                    else -> false
                }
            }
            else -> false
        }
    }
}

class AlphaDash : Rule() {
    override val name: String = "alphaDash"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonLiteral -> {
                when {
                    element.booleanOrNull != null -> false
                    element.isString || element.doubleOrNull != null -> {
                        val regex = Regex(pattern = """[A-Za-zА-Яа-яёЁ0-9_\-]+$""")
                        return regex.matches(input = element.content)
                    }
                    else -> false
                }
            }
            else -> false
        }
    }
}
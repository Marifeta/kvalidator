package kvalidator.rules

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull

class Email : Rule() {
    override val name: String = "email"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonPrimitive -> {
                when {
                    element.booleanOrNull != null -> false
                    element.isString -> {
                        val regex = Regex(
                                pattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                                        "\\@" +
                                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                                        "(" +
                                        "\\." +
                                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                                        ")+"
                        )
                        return regex.matches(input = element.content)
                    }
                    else -> false
                }
            }
            else -> false
        }
    }
}
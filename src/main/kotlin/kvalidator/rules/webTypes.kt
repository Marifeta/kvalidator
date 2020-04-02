package kvalidator.rules

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject

class Url : Rule() {
    override val name: String = "url"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonLiteral -> {
                when {
                    element.booleanOrNull != null -> false
                    element.isString -> {
                        val regex = Regex(
                                pattern = """https?://(www\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-z]{2,63}\b([-a-zA-Z0-9@:%_+.~#?&/=]*)"""
                        )
                        val url = regex.find(input = element.content)?.value.orEmpty()
                        return url.isNotEmpty()
                    }
                    else -> false
                }
            }
            else -> false
        }
    }
}

class Email : Rule() {
    override val name: String = "email"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonLiteral -> {
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

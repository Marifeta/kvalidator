package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull

class Url : Rule() {
    override val name: String = "url"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonPrimitive -> {
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
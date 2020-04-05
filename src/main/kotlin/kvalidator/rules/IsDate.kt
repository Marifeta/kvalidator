package kvalidator.rules

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class IsDate : Rule() {
    override val name: String = "date"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonLiteral -> {
                when {
                    element.isString -> {
                        return try {
                            LocalDateTime.parse(
                                element.content,
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME
                            )
                            true
                        } catch (ex: DateTimeParseException) {
                            false
                        }
                    }
                    else -> false
                }
            }
            else -> false
        }
    }
}
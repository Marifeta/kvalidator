package kvalidator.rules

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.time.format.DateTimeFormatter

class IsDate : Rule() {
    override val name: String = "date"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonLiteral -> {
                when {
                    element.isString -> {
                        // TODO: INCOMPLETE - need all formats
                        return try {
                            LocalDate.parse(element.content, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
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

class AfterDate : Rule() {
    override val name: String = "after:date"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        TODO("Not yet implemented")
    }
}
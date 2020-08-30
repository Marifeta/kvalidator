package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class AfterDate(val date: String) : Rule() {
    override val name: String = "after:date"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if ((data == null) || (!data.containsKey(attribute))) return false
        val element = data[attribute] ?: return false

        if(element !is JsonPrimitive) return false
        
        val dateParsed: LocalDateTime
        val dateAfter: LocalDateTime

        try {
            dateParsed = LocalDateTime.parse(element.content, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            dateAfter = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        } catch (ex: DateTimeParseException) {
            return false
        }

        return dateParsed.isAfter(dateAfter)
    }
}
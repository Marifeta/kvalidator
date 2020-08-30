package kvalidator.rules

import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.time.format.DateTimeFormatter

class IsDate : Rule() {
    override val name: String = "date"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        if (!data.containsKey(attribute)) return true

        return when (val element = data[attribute]) {
            is JsonPrimitive -> {
                when {
                    element.isString -> {
                        return try {
                            val s = DateTimeFormatter.ISO_LOCAL_DATE_TIME
                            val s2 = DateTimeFormatter.ISO_DATE_TIME
                            element.content.let { str ->
                                // check 2020-01-01T00:00.000Z
                                if(str.contains('Z')) {
                                    str.toInstant()
                                } else {
                                    str.toLocalDateTime()
                                }
                            }
                            true
                        } catch (ex: IllegalArgumentException) {
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
package kvalidator

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.time.LocalDate
import java.time.format.DateTimeParseException

abstract class Rule {
    abstract val name: String
    abstract fun validate(data: JsonObject?, attribute: String): Boolean
}

class Size(val value: Int) : Rule() {
    override val name: String = "size"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        val elSize = getSize(data?.get(attribute))
        if (elSize != null && elSize > 0) {
            val userSize = value.toDouble()
            return elSize == userSize
        } else if (elSize == null) {
            return false
        }
        return true
    }
}


class Min(val value: Int) : Rule() {
    override val name: String = "min"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        val elSize = getSize(data?.get(attribute))
        val userSize = value.toDouble()
        if (elSize != null && elSize > 0) {
            return elSize >= userSize
        }
        return true
    }
}

class IsNumeric : Rule() {
    override val name = "numeric"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        return when (val element = data?.get(attribute)) {
            is JsonLiteral -> {
                when {
                    element.isString -> false
                    element.booleanOrNull != null || element.isNull -> false
                    element.doubleOrNull != null -> true
                    else -> false
                }
            }
            else -> false
        }
    }
}

class IsInteger : Rule() {
    override val name = "integer"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        val element = data[attribute]

        return when (element) {
            is JsonLiteral -> {
                when {
                    element.isString -> false
                    element.booleanOrNull != null || data.isNull -> false
                    element.intOrNull != null -> true
                    else -> false
                }
            }
            else -> false
        }
    }
}

class IsString : Rule() {
    override val name = "string"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        val element = data[attribute]

        return when (element) {
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

class Max(val value: Int) : Rule() {
    override val name: String = "max"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        val element = data[attribute]

        val elSize = getSize(element)
        val userSize = value.toDouble()
        if (elSize != null && elSize > 0) {
            return elSize <= userSize
        }
        return true
    }
}

class NotIn(val value: Int, val value2: Int) : Rule() {
    override val name: String = "not_in"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        val element = data[attribute]

        val elSize = getSize(element)
        val userSize = value.toDouble()
        val userSize2 = value2.toDouble()
        if (elSize != null && elSize > 0) {
            return userSize < elSize && elSize < userSize2
        }
        return true
    }
}

class Between(val min: Int, val max: Int) : Rule() {
    override val name: String = "between"

    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        val element = data[attribute]
        val elSize = getSize(element)
        return (elSize != null && min.toDouble() <= elSize && elSize <= max.toDouble())
    }
}

class IsBoolean : Rule() {
    override val name: String = "boolean"
    override fun validate(data: JsonObject?, attribute: String): Boolean {

        return when (val elemnet = data?.get(attribute)) {

            is JsonLiteral -> {
                when {
                    elemnet.isString -> false
                    elemnet.doubleOrNull != null -> false
                    elemnet.booleanOrNull != null -> true
                    else -> false
                }
            }
            else -> false
        }
    }
}

class Alpha : Rule() {
    override val name: String = "alpha"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        val element = data?.get(attribute)

        return when (element) {
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
        val element = data?.get(attribute)

        return when (element) {
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
        val element = data?.get(attribute)

        return when (element) {
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

class Url : Rule() {
    override val name: String = "url"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        val element = data?.get(attribute)

        return when (element) {
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
        val element = data?.get(attribute)

        return when (element) {
            is JsonLiteral -> {
                when {
                    element.booleanOrNull != null -> false
                    element.isString -> {
                        val regex = Regex(
                                pattern = """^(?!\.)\("([^"\r\\]|\\["\r\\])*"|\([-a-z0-9!#${'$'}%&'*+/=?^_`{|}~] |\(?@[a-z0-9][\w.-]*[a-z0-9]\.[a-z][a-z.]*[a-z]$"""
                        )
                        val email = regex.find(input = element.content)?.value.orEmpty()
                        return email.isNotEmpty()
                    }
                    else -> false
                }
            }
            else -> false
        }
    }
}

class Accepted : Rule() {
    override val name: String = "accepted"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        val element = data?.get(attribute)

        return when (element) {
            is JsonPrimitive -> {
                val c = data as JsonLiteral
                when {
                    c.isString && element.content == "yes" -> true
                    c.isString && element.content == "no" -> true
                    c.isString && element.content == "1" -> true
                    data.boolean -> data.boolean
                    data.doubleOrNull != null && data.double == 1.00 -> true
                    else -> false
                }
            }
            else -> false
        }
    }
}

class IsArray: Rule() {
    override val name: String = "is_array"
    override fun validate(data: JsonObject?, attribute: String): Boolean {

        return when (data?.get(attribute)) {
            is JsonArray -> {
                return true
            }
            else -> false
        }
    }
}

class IsDate : Rule() {
    override val name: String = "date"
    override fun validate(data: JsonObject?, attribute: String): Boolean {

        return when (val element = data?.get(attribute)) {
            is JsonLiteral -> {
                when {
                    element.isString -> {
                        // проверяем точки, количество цифр, заменяем точки на тире и парсим дату
                        // TODO is_not_mpp
                        // TODO need all formats
                        return try {
                            LocalDate.parse(element.content)
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

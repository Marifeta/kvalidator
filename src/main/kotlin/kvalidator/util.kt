package kvalidator

import kotlinx.serialization.json.*
import kvalidator.rules.*


fun getSize(value: JsonElement?): Double? {
    return when (value) {
        is JsonPrimitive -> {
            when {
                value.isString -> value.content.length.toDouble()
                value.doubleOrNull != null -> value.double
                value.booleanOrNull != null || value is JsonNull -> null
                else -> null
            }
        }
        is JsonObject -> value.size.toDouble()
        is JsonArray -> value.size.toDouble()
        else -> null
    }
}

fun parseRule(value: String): Rule {
    val parts = value.split(":")
    return when (parts.first().toLowerCase()) {
        "between" -> {
            val args = parts[1].split(',')
            Between(args.first().toInt(), args.last().toInt())
        }
        "max" -> Max(parts[1].toInt())
        "min" -> Min(parts[1].toInt())
        "size" -> Size(parts[1].toInt())
        "accepted" -> Accepted()
        "string" -> Accepted()
        "alpha" -> Alpha()
        "alpha_dash" -> AlphaDash()
        "alpha_num" -> AlphaNum()
        "email" -> Email()
        "required" -> Required()
        "numeric" -> IsNumeric()
        "date" -> IsDate()
        "boolean" -> IsBoolean()
        "integer" -> IsInteger()
        "url" -> Url()
        else -> throw IllegalArgumentException("unknown rule")
    }
}

fun parseRules(value: String): List<Rule> {
    return value.split("|").map { parseRule(it.trim()) }
}

fun stringifyRule(rule: Rule) : String {
    return when(rule) {
        is Between -> "${rule.name}:${rule.min},${rule.max}"
        is Max -> "${rule.name}:${rule.value}"
        is Min -> "${rule.name}:${rule.value}"
        is Size -> "${rule.name}:${rule.value}"
        else -> rule.name
    }
}

fun stringifyRules(rules: List<Rule>) : String {
    return rules.map { stringifyRule(it) }.joinToString("|")
}
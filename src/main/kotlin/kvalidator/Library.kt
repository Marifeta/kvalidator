package kvalidator

import kotlinx.serialization.json.*
import kvalidator.i18n.Dictionary
import kvalidator.i18n.Lang
import kvalidator.i18n.en
import kvalidator.rules.Rule

class Error {
    val errors: MutableMap<String, MutableList<String>> = mutableMapOf()

    fun add(attribute: String, reason: String): Unit {
        if (errors.contains(attribute)) {
            errors[attribute]?.add(reason)
        } else {
            errors[attribute] = mutableListOf(reason)
        }
    }

    fun first(attribute: String): String? {
        if (errors.contains(attribute)) {
            return errors[attribute]?.get(0)
        }
        return null
    }

    fun get(attribute: String): List<String>? {
        if (errors.contains(attribute)) {
            return errors[attribute]?.toList()
        }
        return null
    }

    fun all(): MutableMap<String, MutableList<String>>? {
        if (errors.isEmpty()) {
            return null
        }
        return errors
    }

    val count: Int
        get() = errors.size
}

class Validator(
    val data: JsonElement = JsonObject(emptyMap()),
    val rules: Map<String, List<Rule>> = emptyMap(),
    private val lang: Dictionary = en
) {
    private val inputErrors = Error()

    // attribute_name: [reason1, reason2, reason3]
    val errors: Map<String, List<String>>
        get() = inputErrors.errors

    fun validate(): Boolean {
        when (data) {
            is JsonNull -> println("null")
            is JsonArray -> println("array")
            is JsonObject -> {
                rules.forEach { (attribute, ruleItems) ->
                    ruleItems.forEach { rule ->
                        val isValid = rule.validate(data, attribute)
                        if (!isValid) {
                            inputErrors.add(
                                attribute = attribute,
                                reason = Lang.message(lang, rule, data, attribute)
                            )
                        }
                    }
                }
            }
            is JsonLiteral -> println("null")
        }
        return inputErrors.count == 0
    }
}
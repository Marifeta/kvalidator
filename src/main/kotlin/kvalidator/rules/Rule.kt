package kvalidator.rules

import kotlinx.serialization.json.JsonObject

abstract class Rule {
    abstract val name: String
    abstract fun validate(data: JsonObject?, attribute: String): Boolean
}


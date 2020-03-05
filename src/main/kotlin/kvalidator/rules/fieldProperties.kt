package kvalidator.rules

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

class Required : Rule() {
    override val name: String = "required"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        TODO("Not yet implemented")
        // if (data == null) return false
        // if (!data.containsKey(attribute)) return false
        //
        // return when (val element = data[attribute]) {
        //     is JsonLiteral -> {
        //         when {
        //             element.isString -> element.content.length.toDouble() > 0
        //             element.doubleOrNull != null -> true
        //             element.booleanOrNull != null -> true
        //             else -> false
        //         }
        //     }
        //     else -> false
        // }
    }
}

class Confirmed : Rule() {
    override val name: String = "confirmed"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        TODO("Not yet implemented")
    }
}

class Accepted : Rule() {
    override val name: String = "accepted"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        return when (val element = data?.get(attribute)) {
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


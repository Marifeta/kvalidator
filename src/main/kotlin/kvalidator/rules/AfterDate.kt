package kvalidator.rules

import kotlinx.serialization.json.JsonObject

class AfterDate : Rule() {
    override val name: String = "after:date"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        TODO("Not yet implemented")
    }
}
package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kvalidator.getSize

class Between(val min: Int, val max: Int) : Rule() {
    override val name: String = "between"

    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        val element = data[attribute]
        val elSize = getSize(element)
        return (elSize != null && min.toDouble() <= elSize && elSize <= max.toDouble())
    }
}
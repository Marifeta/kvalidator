package kvalidator.rules

import kotlinx.serialization.json.JsonObject
import kvalidator.getSize

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
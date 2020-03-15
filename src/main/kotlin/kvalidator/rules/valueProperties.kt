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

class Min(private val value: Int) : Rule() {
    override val name: String = "min"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        val elSize = getSize(data?.get(attribute))
        val userSize = value.toDouble()
        if (elSize != null) {
            return elSize >= userSize
        }
        return true
    }
}

class Max(private val value: Int) : Rule() {
    override val name: String = "max"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return true
        val element = data[attribute]

        val elSize = getSize(element)
        val userSize = value.toDouble()
        if (elSize != null) {
            return elSize <= userSize
        }
        return false
    }
}

class Size(private val value: Int) : Rule() {
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


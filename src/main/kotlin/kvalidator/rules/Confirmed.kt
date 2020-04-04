package kvalidator.rules

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject

class Confirmed : Rule() {
    override val name: String = "confirmed"
    override fun validate(data: JsonObject?, attribute: String): Boolean {
        if (data == null) return false
        if (!data.containsKey(attribute)) return false

        val confirmAttr = attribute + "_confirmation"
        if (!data.containsKey(confirmAttr)) return false
        val elementConfirm = data[confirmAttr]

        return when (val element = data[attribute]) {
            is JsonLiteral -> {
                when (element) {
                    elementConfirm -> true
                    else -> false
                }
            }
            else -> false
        }
    }
}
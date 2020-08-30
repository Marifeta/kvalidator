package kvalidator

import kotlinx.serialization.json.*
import kotlinx.serialization.json.Json

open class LibraryTest {
    private val json = Json { allowStructuredMapKeys = true }
    internal val data = this::class.java.getResource("/data.json").readText().let { json.parseToJsonElement(it) }.jsonObject
}


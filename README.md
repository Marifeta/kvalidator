# **kvalidator**

## Why use kvalidator?

* Readable and declarative validation rules.
* Error messages with multilingual support.

Support platform:
 * JVM
 * Android
 
## Installation
```kts

Required dependencies 
- kotlin 1.4
- kotlinx.serialization: min 1.0.0-RC

repositories {
    jcenter()
}

dependencies {
    implementation("com.github.marifeta:kvalidator:1.0.0")
}
```

## Usage

#### Validate
```kt

val testJson = JsonObject(mapOf(
        "name" to JsonPrimitive("John"),
        "age" to JsonPrimitive(28),
        "isHuman" to JsonPrimitive(true),
))

val rules = mapOf<String, List<Rule>>(
        "name" to listOf(Alpha(), Required()),
        "age" to listOf(IsInteger(), Between(25, 48)),
        "isHuman" to listOf(IsBoolean()),
)

val validator = Validator(testJson, rules, /* lang = en */)
validator.validate() // true 

// or
if(!validator.validate()) {
    validator.errors.forEach {(attribute, messages)->
        messages.forEach { msg ->
            println("Error: $attribute has error by $msg")
        }
    }
}

```
#### Errors
```kt

val firstError = validator.errors.first() // firstError or null
val validator.errors // mutableListOf(reason) or null
val errorCount = validator.errors.count // errors.size (int)

```
#### Utils
```kt
parseRule("max:255"): Rule
parseRules("required|max:255"): List<Rule>
stringifyRules("required|email|max:15|min:8|size:8|boolean")
stringifyRule("max:15")
```


## Available Rules

#### Alpha
 The field under validation must be entirely alphabetic characters.
####  AlphaNum
 The field under validation must be entirely alpha-numeric characters.
#### AlphaDash
 The field under validation may have alpha-numeric characters, as well as dashes and underscores.
#### IsInteger
 The field under validation must have an integer value.
#### IsString
 The field under validation must be a string.
#### IsBoolean
 The field under validation must be a boolean value of the form true, false (Not String).
#### IsArray
 The field under validation must be an array.
#### IsNumeric
 Validate that an attribute is numeric. The string representation of a number will pass.
#### IsDate
 The field under validation must be a valid date format which is acceptable by Java's LocalDate object in format "yyyy-mm-ddThh:mm:ss".
#### AfterDate
 The field under validation must be after the given date.
#### Required
 Checks if the length of the String representation of the value is >
#### Confirmed
 The field under validation must have a matching field of foo_confirmation. For example, if the field under validation is password, a matching password_confirmation field must be present in the input.
#### Accepted
 The field under validation must be yes, on, 1 or true. This is useful for validating "Terms of Service" acceptance.
#### Between
 The field under validation must have a size between the given min and max. Strings, numerics, and files are evaluated in the same fashion as the size rule.
#### Min
 Validate that an attribute is at least a given size.
#### Max
 Validate that an attribute is no greater than a given size
#### Size
 The field under validation must have a size matching the given value. For string data, value corresponds to the number of characters. For numeric data, value corresponds to a given integer value.
#### Url
 Validate that an attribute has a valid URL format
#### Email
 The field under validation must be formatted as an e-mail address.

## Links
- [Available Validation Rules](https://laravel.com/docs/5.8/validation#available-validation-rules)
- [Описание правил на русском](https://laravel.ru/docs/v5/validation#%D0%B4%D0%BE%D1%81%D1%82%D1%83%D0%BF%D0%BD%D1%8B%D0%B5][https://laravel.ru/docs/v5/validation#%D0%B4%D0%BE%D1%81%D1%82%D1%83%D0%BF%D0%BD%D1%8B%D0%B5)
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)

## License
 MIT

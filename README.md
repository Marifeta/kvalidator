# kvalidator

## Why use kvalidator?

* Works anywhere and everywhere. [llvn\native, browser, jvm, android, etc]
* Readable and declarative validation rules.
* Error messages with multilingual support.

## Installation
```kts
repositories {
    jcenter()
}

dependencies {
    implementation("com.github.marifeta:kvalidator:0.0.1")
}
```

## Usage

```kt

val rules = mapOf<String, List<Rule>>(
        "one_number" to listOf(Between(5, 30)),
        "one_string" to listOf(Between(3, 7))
)

val validator = Validator(somethingKotlinJsonData, rules)        

if(!validator.validate()) {
    validator.errors.forEach {(attribute, messages)->
        messages.forEach { msg ->
            println("Error: $attribute has error by $msg")
        }
    }
}

```
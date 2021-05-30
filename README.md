# android-input-validation-kotlin
Provides Easy to use Input validation in Kotlin

This project provides easy to use Input validation

You can perform simple to complex input validation (with reusability) by using simple operators. (and/or/not)

For usage please refer to 'com.example.myapplication.ui.main.MainFragment' class


val result = Rules.alphabetsOnly("some error message").validate("abcd")

if (result.status == Status.SUCCESS) {
    //We are good to go
}
else {
    //show result.getErrorMessage()
}


Simple operators can be used to define complex validations

val ruleA = Rules.minLength(4, "Minimum length required is 4")
val ruleB = Rules.maxLength(4, "Minimum length allowed 10")
val ruleC = Rules.alphabetsOnly("Only Alphabets are allowed")

val combinedRule = ruleA and ruleB and ruleC

val result = combinedRule.validate("Input")

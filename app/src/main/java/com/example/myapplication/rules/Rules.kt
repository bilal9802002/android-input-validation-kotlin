package com.example.myapplication.rules

import com.example.myapplication.extensions.doubleOrNull
import com.example.myapplication.rules.base.BaseRule
import com.example.myapplication.rules.base.Rule
import java.util.*

/**
 * Contains Rules to apply on passed input string
 */
sealed class Rules {
    companion object {

        /**
         * Creates a Rule object with passed callback & errorMessage
         * callback: Give input string for custom validation and must return true (success) / false (error)
         * Callback will be called when validate function is called with input value
         */
        fun create(callback: ((input: String) -> Boolean), errorMessage: String? = null): Rule {
            return object : BaseRule(errorMessage) {
                override fun performValidation(input: String): Boolean {
                    return callback.invoke(input)
                }
            }
        }

        fun notEmpty(errorMessage: String? = null): Rule {
            return create({ it.trim().isNotEmpty() }, errorMessage)
        }

        fun notBlank(errorMessage: String? = null): Rule {
            return create({ it.isNotBlank() }, errorMessage)
        }

        fun patternMatch(pattern: String, errorMessage: String? = null): Rule {
            return create({ it.matches(pattern.toRegex()) }, errorMessage)
        }

        fun contains(target: String, errorMessage: String? = null): Rule {
            return create({ it.contains(target) }, errorMessage)
        }

        fun equalsTo(target: String, errorMessage: String? = null): Rule {
            return create({ it == target }, errorMessage)
        }

        fun alphabetsOnly(errorMessage: String? = null): Rule {
            return patternMatch("^[a-zA-Z\\s]*\$", errorMessage)
        }

        fun allLowerCase(errorMessage: String? = null): Rule {
            return create({ it == it.toLowerCase(Locale.getDefault()) }, errorMessage)
        }

        fun allUpperCase(errorMessage: String? = null): Rule {
            return create({ it == it.toUpperCase(Locale.getDefault()) }, errorMessage)
        }

        fun greaterThanNumber(target: Double, errorMessage: String? = null): Rule {
            return create({
                it.doubleOrNull()?.let { value ->
                    return@create value.compareTo(target) > 0
                } ?: false
            }, errorMessage)
        }

        fun lessThanNumber(target: Double, errorMessage: String? = null): Rule {
            return create({
                it.doubleOrNull()?.let { value ->
                    return@create value.compareTo(target) < 0
                } ?: false
            }, errorMessage)
        }

        fun numbersRange(
            range: ClosedFloatingPointRange<Double>,
            errorMessage: String? = null
        ): Rule {
            return create({
                it.doubleOrNull()?.let { value ->
                    return@create value in range
                } ?: false
            }, errorMessage)
        }

        fun minLength(length: Int, errorMessage: String? = null): Rule {
            return create({ it.length >= length }, errorMessage)
        }

        fun maxLength(length: Int, errorMessage: String? = null): Rule {
            return create({ it.length <= length }, errorMessage)
        }

        fun exactLength(length: Int, errorMessage: String? = null): Rule {
            return create({ it.length == length }, errorMessage)
        }

        fun lengthRange(intRange: IntRange, errorMessage: String? = null): Rule {
            return create({ it.length in intRange }, errorMessage)
        }
    }
}
package com.example.myapplication.rules

import com.example.myapplication.rules.base.Rule

/**
 * Basic Operators to be performed on set of rules
 * AND OR and NOT along with Optional
 */
sealed class Operator {
    /**
     * Take two rules to perform AND operation
     */
    class AND(private val ruleA: Rule, private val ruleB: Rule) : Rule {
        override fun validate(input: String?): ValidationResult {
            val result = ruleA.validate(input)
            return when (result.status) {
                Status.SUCCESS -> ruleB.validate(input)
                Status.ERROR -> result
            }
        }
    }

    /**
     * Take two rules to perform OR operation
     */
    class OR(private val ruleA: Rule, private val ruleB: Rule) : Rule {
        override fun validate(input: String?): ValidationResult {
            val result = ruleA.validate(input)
            return when (result.status) {
                Status.SUCCESS -> result
                Status.ERROR -> ruleB.validate(input)
            }
        }
    }

    /**
     * Negate the given rule, if success it will return false and vice-versa
     */
    class NOT(private val rule: Rule) : Rule {
        override fun validate(input: String?): ValidationResult {
            val result = rule.validate(input)
            return when (result.status) {
                Status.SUCCESS -> ValidationResult(input, rule, Status.ERROR)
                Status.ERROR -> ValidationResult(input, rule, Status.SUCCESS)
            }
        }
    }

    /**
     * Make a rule optional in-case input is null or empty
     */
    class Optional(private val rule: Rule) : Rule {
        override fun validate(input: String?): ValidationResult {
            if (input.isNullOrEmpty())
                return ValidationResult(input, rule, Status.SUCCESS)

            return rule.validate(input)
        }
    }
}
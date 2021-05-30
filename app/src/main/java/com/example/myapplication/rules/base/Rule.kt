package com.example.myapplication.rules.base

import com.example.myapplication.rules.Operator
import com.example.myapplication.rules.ValidationResult

/**
 * Rule interface
 */
interface Rule {

    fun validate(input: String?): ValidationResult

    //Operators
    infix fun and(rule: Rule): Rule = Operator.AND(this, rule)
    infix fun or(rule: Rule): Rule = Operator.OR(this, rule)

    operator fun not(): Rule = Operator.NOT(this)

}
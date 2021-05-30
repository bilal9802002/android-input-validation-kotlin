package com.example.myapplication.rules.base

import com.example.myapplication.rules.Status
import com.example.myapplication.rules.ValidationResult

/**
 * Base class for Rule
 * Override performValidation method and implement your own rule
 */
abstract class BaseRule(val errorMessage: String? = null) : Rule {

    final override fun validate(input: String?): ValidationResult {
        val safeInput = input ?: ""
        if (performValidation(safeInput))
            return ValidationResult(safeInput, this, Status.SUCCESS)

        return ValidationResult(safeInput, this, Status.ERROR)
    }

    internal abstract fun performValidation(input: String): Boolean
}
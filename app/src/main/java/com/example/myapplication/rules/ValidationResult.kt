package com.example.myapplication.rules

import com.example.myapplication.rules.base.BaseRule
import com.example.myapplication.rules.base.Rule

data class ValidationResult(
    val input: String?,
    val rule: Rule,
    val status: Status
) {
    fun getErrorMessage(): String? {
        return (rule as? BaseRule)?.errorMessage
    }
}

enum class Status {
    SUCCESS,
    ERROR
}
package com.example.expensesvisualizerapp.presentation.statevalidation

sealed class ValidationState {
    data object Idle : ValidationState()
    data object Valid : ValidationState()
    data class Invalid(val errorMessage: String) : ValidationState()
}
package com.example.expensesvisualizerapp.presentation.actions

sealed interface ExpensesActions {
    data class OnDescriptionChanged(val value: String) : ExpensesActions
    data class OnAmountChanged(val value: String) : ExpensesActions
}
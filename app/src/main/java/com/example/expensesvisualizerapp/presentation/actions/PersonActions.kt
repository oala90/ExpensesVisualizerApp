package com.example.expensesvisualizerapp.presentation.actions

sealed interface PersonActions {
    data class OnNameChanged(val value: String) : PersonActions
    data class OnAgeChanged(val value: Int) : PersonActions
    data class OnPositionChanged(val value: String) : PersonActions
    data class OnBudgetChanged(val value: Int) : PersonActions
}
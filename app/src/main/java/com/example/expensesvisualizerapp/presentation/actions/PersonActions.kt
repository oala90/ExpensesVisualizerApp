package com.example.expensesvisualizerapp.presentation.actions

import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity

sealed interface PersonActions {
    data class OnNameChanged(val value: String) : PersonActions
    data class OnAgeChanged(val value: String) : PersonActions
    data class OnPositionChanged(val value: String) : PersonActions
    data class OnBudgetChanged(val value: String) : PersonActions
    data class OnExpensesChanged(val value: List<ExpensesEntity>) : PersonActions
}
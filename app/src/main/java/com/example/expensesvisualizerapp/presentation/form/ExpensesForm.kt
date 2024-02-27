package com.example.expensesvisualizerapp.presentation.form

data class ExpensesForm(
    val id: String = "",
    val personId: Long = 0L,
    val description: String = "",
    val amount: String = ""
)
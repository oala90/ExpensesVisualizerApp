package com.example.expensesvisualizerapp.domain.entities

data class ExpensesEntity(
    val id: String = "",
    val personId: Long = 0L,
    val description: String = "",
    val amount: Double = 0.0
)
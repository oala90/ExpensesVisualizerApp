package com.example.expensesvisualizerapp.presentation.form

import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity

data class PersonForm(
    val id: Long = 0L,
    val name: String = "",
    val age: String = "",
    val position: String = "",
    val budget: String = "",
    val expenses: List<ExpensesEntity> = emptyList()
)

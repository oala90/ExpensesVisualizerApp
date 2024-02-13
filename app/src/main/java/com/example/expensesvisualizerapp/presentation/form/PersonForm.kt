package com.example.expensesvisualizerapp.presentation.form

import com.example.expensesvisualizerapp.data.dto.Expenses
import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity

data class PersonForm(
    val id: Long = 0L,
    val name: String? = "",
    val age: Int = 0,
    val position: String? = "",
    val budget: Int = 0,
    val expenses: List<ExpensesEntity> = emptyList()
)

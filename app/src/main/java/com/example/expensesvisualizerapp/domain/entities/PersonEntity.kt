package com.example.expensesvisualizerapp.domain.entities

data class PersonEntity(
    val id: Long = 0L,
    val name: String = "",
    val age: Int = 0,
    val position: String = "",
    val budget: Double = 0.0,
    val expenses: List<ExpensesEntity> = emptyList()
)
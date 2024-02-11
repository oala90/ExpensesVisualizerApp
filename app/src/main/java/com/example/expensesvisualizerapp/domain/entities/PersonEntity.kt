package com.example.expensesvisualizerapp.domain.entities

import com.example.expensesvisualizerapp.data.dto.Expenses

data class PersonEntity(
    val id: Long,
    val name: String,
    val age: Int,
    val position: String,
    val budget: Int,
//    val expenses: List<Expenses>?
)
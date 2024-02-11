package com.example.expensesvisualizerapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ExpensesEntity(
    val id: Long,
    val personId: Long,
    val description: String,
    val amount: Int
)
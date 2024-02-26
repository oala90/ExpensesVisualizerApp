package com.example.expensesvisualizerapp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expenses(
    @PrimaryKey val id: String,
    val personId: Long,
    val description: String,
    val amount: Double
)
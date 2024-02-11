package com.example.expensesvisualizerapp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expenses(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val personId: Long,
    val description: String,
    val amount: Int
)
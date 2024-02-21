package com.example.expensesvisualizerapp.data.dto

import androidx.room.PrimaryKey

data class Expenses(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val personId: Long,
    val description: String,
    val amount: Int
)
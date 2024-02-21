package com.example.expensesvisualizerapp.presentation.form

import androidx.room.PrimaryKey

data class ExpensesForm(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val personId: Long = 0L,
    val description: String = "",
    val amount: Int = 0
)
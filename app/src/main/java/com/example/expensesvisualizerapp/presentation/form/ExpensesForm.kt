package com.example.expensesvisualizerapp.presentation.form

import androidx.room.PrimaryKey

data class ExpensesForm(
//    @PrimaryKey(autoGenerate = true)
    val id: String = "",
    val personId: Long = 0L,
    val description: String = "",
    val amount: String = ""
)
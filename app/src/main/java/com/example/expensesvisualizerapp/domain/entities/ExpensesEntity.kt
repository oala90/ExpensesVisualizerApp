package com.example.expensesvisualizerapp.domain.entities

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExpensesEntity(
//    @PrimaryKey(autoGenerate = true)
    val id: String = "",
    val personId: Long = 0L,
    val description: String = "",
    val amount: Double = 0.0
): Parcelable
package com.example.expensesvisualizerapp.domain.entities

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExpensesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val personId: Long,
    val description: String,
    val amount: Int
): Parcelable
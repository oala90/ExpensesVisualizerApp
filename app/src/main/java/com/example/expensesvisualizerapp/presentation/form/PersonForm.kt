package com.example.expensesvisualizerapp.presentation.form

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonForm(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String = "",
    val age: Int = 0,
    val position: String = "",
    val budget: Int = 0,
    val expenses: List<ExpensesEntity> = emptyList()
) : Parcelable

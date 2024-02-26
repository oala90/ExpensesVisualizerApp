package com.example.expensesvisualizerapp.presentation.form

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonForm(
//    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String = "",
    val age: String = "",
    val position: String = "",
    val budget: String = "",
    val expenses: List<ExpensesEntity> = emptyList()
) : Parcelable

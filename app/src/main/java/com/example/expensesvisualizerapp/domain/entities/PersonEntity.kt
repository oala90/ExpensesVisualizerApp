package com.example.expensesvisualizerapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensesvisualizerapp.data.dto.Expenses

//@Entity(tableName = "persons")
data class PersonEntity(
    val id: Long,
    val name: String,
    val age: Int,
    val position: String,
    val budget: Int,
//    val expenses: List<Expenses>?
)
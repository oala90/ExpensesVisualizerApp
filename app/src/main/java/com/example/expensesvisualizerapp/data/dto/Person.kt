package com.example.expensesvisualizerapp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensesvisualizerapp.data.dto.Expenses

@Entity(tableName = "persons")
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val age: Int,
    val position: String,
    val budget: Int,
//    val expenses: List<Expenses>?
)
package com.example.expensesvisualizerapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.expensesvisualizerapp.data.dto.Expenses
import com.example.expensesvisualizerapp.data.dto.Person

@Dao
interface ExpensesDao {

    @Query(value = "SELECT * FROM expenses WHERE personId = :personId")
    suspend fun getAllExpenses(personId: Long): List<Expenses>

    @Insert
    suspend fun insertExpense(expenses: Expenses)

//    @Update
//    fun updateExpense(expenses: Expenses)
//
//    @Delete
//    fun deleteExpense(expenses: Expenses)
}
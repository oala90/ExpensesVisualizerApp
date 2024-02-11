package com.example.expensesvisualizerapp.domain.repositories

import com.example.expensesvisualizerapp.data.dto.Expenses

interface ExpensesRepository {
    suspend fun getAllExpenses(personId: Long): List<Expenses>
    suspend fun insertExpense(expenses: Expenses)
//    suspend fun updateExpense(expenses: Expenses)
//    suspend fun deleteExpense(expenses: Expenses)
}
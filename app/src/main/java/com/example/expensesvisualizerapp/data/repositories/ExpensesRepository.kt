package com.example.expensesvisualizerapp.data.repositories

import com.example.expensesvisualizerapp.data.dao.ExpensesDao
import com.example.expensesvisualizerapp.data.dto.Expenses
import com.example.expensesvisualizerapp.domain.repositories.ExpensesRepository

class ExpensesRepositoryImpl(
    private val expensesDao: ExpensesDao
) : ExpensesRepository {
    override suspend fun getAllExpenses(personId: Long): List<Expenses> = expensesDao.getAllExpenses(personId)
    override suspend fun insertExpense(expenses: Expenses) = expensesDao.insertExpense(expenses)
}
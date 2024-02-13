package com.example.expensesvisualizerapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expensesvisualizerapp.data.dao.ExpensesDao
import com.example.expensesvisualizerapp.data.dao.PersonDao
import com.example.expensesvisualizerapp.data.dto.Expenses
import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.data.local.Converter

@Database(entities = [Person::class, Expenses::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun expensesDao(): ExpensesDao
}
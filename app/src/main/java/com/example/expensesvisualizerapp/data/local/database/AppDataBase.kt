package com.example.expensesvisualizerapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expensesvisualizerapp.data.dao.ExpensesDao
import com.example.expensesvisualizerapp.data.dao.PersonDao
import com.example.expensesvisualizerapp.data.dto.Expenses
import com.example.expensesvisualizerapp.data.dto.Person

@Database(entities = [Person::class, Expenses::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun expensesDao(): ExpensesDao

//    companion object {
//        @Volatile
//        private var INSTANCE: AppDataBase? = null
//
//        fun getDataBase(context: Context): AppDataBase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDataBase::class.java,
//                    "app_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}
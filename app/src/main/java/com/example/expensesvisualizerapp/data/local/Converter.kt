package com.example.expensesvisualizerapp.data.local

import androidx.room.TypeConverter
import com.example.expensesvisualizerapp.data.dto.Expenses
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun fromString(value: String): List<Expenses> {
        val listType = object : TypeToken<List<Expenses>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Expenses>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
package com.example.expensesvisualizerapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.expensesvisualizerapp.data.dto.Expenses
import com.example.expensesvisualizerapp.data.dto.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query(value = "SELECT * FROM persons")
    fun getAllPeople(): Flow<List<Person>>

    @Query(value = "SELECT * FROM persons WHERE id =:personId")
    fun getPersonDetails(personId: Long?): Flow<Person>

    @Query(value = "Update persons set expenses =:expenses Where id=:personId")
    suspend fun saveExpenses(expenses: List<Expenses>?, personId: Long?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(person: Person)

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)
}
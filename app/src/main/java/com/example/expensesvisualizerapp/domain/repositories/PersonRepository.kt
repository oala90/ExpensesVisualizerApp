package com.example.expensesvisualizerapp.domain.repositories

import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun getAllPeople(): Flow<List<PersonEntity>>
    suspend fun getPersonDetails(personId: Long?): Flow<PersonEntity>
    suspend fun insertPerson(person: PersonEntity)
    suspend fun updatePerson(person: PersonEntity)
    suspend fun deletePerson(person: PersonEntity)
    suspend fun  saveExpenses(person: PersonEntity)
}
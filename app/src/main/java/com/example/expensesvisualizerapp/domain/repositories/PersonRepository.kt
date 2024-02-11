package com.example.expensesvisualizerapp.domain.repositories

import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.domain.entities.PersonEntity

interface PersonRepository {
    suspend fun getAllPeople(): List<PersonEntity>
    suspend fun insertPerson(person: Person)
    suspend fun updatePerson(person: Person)
    suspend fun deletePerson(person: Person)
}
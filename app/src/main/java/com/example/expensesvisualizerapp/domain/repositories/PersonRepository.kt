package com.example.expensesvisualizerapp.domain.repositories

import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.domain.entities.PersonEntity

interface PersonRepository {
    suspend fun getAllPeople(): List<PersonEntity>
    suspend fun insertPerson(person: PersonEntity)
    suspend fun updatePerson(person: PersonEntity)
    suspend fun deletePerson(person: PersonEntity)
}
package com.example.expensesvisualizerapp.data.repositories

import com.example.expensesvisualizerapp.data.dao.PersonDao
import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.data.mapper.toDTO
import com.example.expensesvisualizerapp.data.mapper.toEntity
import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.domain.repositories.PersonRepository
import javax.inject.Inject

internal class PersonRepositoryImpl @Inject constructor(
    private val personLocalDataSource: PersonLocalDataSource
) : PersonRepository {
    override suspend fun getAllPeople(): List<PersonEntity> = personLocalDataSource.getAllPeople().toEntity()
    override suspend fun insertPerson(person: PersonEntity) = personLocalDataSource.insertPerson(person.toDTO())
    override suspend fun updatePerson(person: PersonEntity) = personLocalDataSource.updatePerson(person.toDTO())
    override suspend fun deletePerson(person: PersonEntity) = personLocalDataSource.deletePerson(person.toDTO())
}

internal interface PersonLocalDataSource {
    suspend fun getAllPeople(): List<Person>
    suspend fun insertPerson(person: Person)
    suspend fun updatePerson(person: Person)
    suspend fun deletePerson(person: Person)
}
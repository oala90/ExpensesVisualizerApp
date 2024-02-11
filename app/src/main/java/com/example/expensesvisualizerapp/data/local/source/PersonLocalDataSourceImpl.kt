package com.example.expensesvisualizerapp.data.local.source

import com.example.expensesvisualizerapp.data.dao.PersonDao
import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.data.repositories.PersonLocalDataSource
import javax.inject.Inject

class PersonLocalDataSourceImpl @Inject constructor(
    private val personDao: PersonDao
): PersonLocalDataSource {
    override suspend fun getAllPeople(): List<Person> {
        return personDao.getAllPeople()
    }

    override suspend fun insertPerson(person: Person) {
        return personDao.insertPerson(person)
    }

    override suspend fun updatePerson(person: Person) {
        return personDao.updatePerson(person)
    }

    override suspend fun deletePerson(person: Person) {
        return personDao.deletePerson(person)
    }
}
package com.example.expensesvisualizerapp.data.local.source

import com.example.expensesvisualizerapp.data.dao.PersonDao
import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.data.repositories.PersonLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.collections.List as List

class PersonLocalDataSourceImpl @Inject constructor(
    private val personDao: PersonDao
): PersonLocalDataSource {
    override suspend fun getAllPeople(): Flow<List<Person>> {
        return personDao.getAllPeople()
    }

    override suspend fun getPersonDetails(personId: Long?): Flow<Person> {
        return personDao.getPersonDetails(personId = personId)
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

    override suspend fun saveExpenses(person: Person) {
        return personDao.saveExpenses(personId = person.id, expenses = person.expenses)
    }
}
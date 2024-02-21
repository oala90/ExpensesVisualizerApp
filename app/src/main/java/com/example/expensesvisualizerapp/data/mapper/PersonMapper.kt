package com.example.expensesvisualizerapp.data.mapper

import com.example.expensesvisualizerapp.data.dto.Expenses
import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity
import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<Person>>.toEntity(): Flow<List<PersonEntity>> {
    return this.map { people ->
        people.map { person ->
            PersonEntity(
                id = person.id,
                name = person.name,
                age = person.age,
                position = person.position,
                budget = person.budget,
                expenses = person.expenses.map { expense ->
                    ExpensesEntity(
                        id = expense.id,
                        personId = expense.personId,
                        description = expense.description,
                        amount = expense.amount
                    )
                }
            )
        }
    }
}

fun PersonEntity.toDTO(): Person {
    return Person(
        id = this.id,
        name = this.name,
        age = this.age,
        position = this.position,
        budget = this.budget,
        expenses = this.expenses.map {
            Expenses(
                id = it.id,
                personId = it.personId,
                description = it.description,
                amount = it.amount
            )
        }
    )
}

fun Flow<Person>.toPersonEntity(): Flow<PersonEntity> {
    return this.map { person ->
        PersonEntity(
            id = person.id,
            name = person.name,
            age = person.age,
            position = person.position,
            budget = person.budget,
            expenses = person.expenses.map {
                ExpensesEntity(
                    id = it.id,
                    personId = it.personId,
                    description = it.description,
                    amount = it.amount
                )
            }
        )
    }
}
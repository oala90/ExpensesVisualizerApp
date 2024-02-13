package com.example.expensesvisualizerapp.data.mapper

import com.example.expensesvisualizerapp.data.dto.Expenses
import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity
import com.example.expensesvisualizerapp.domain.entities.PersonEntity

fun List<Person>.toEntity(): List<PersonEntity> {
    return this.map { value ->
        PersonEntity(
            id = value.id,
            name = value.name,
            age = value.age,
            position = value.position,
            budget =value.budget,
            expenses = value.expenses?.map {
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

fun PersonEntity.toDTO(): Person {
    return Person(
        id = this.id,
        name = this.name,
        age = this.age,
        position = this.position,
        budget = this.budget,
        expenses = this.expenses?.map {
            Expenses(
                id = it.id,
                personId = it.personId,
                description = it.description,
                amount = it.amount
            )
        }
    )
}
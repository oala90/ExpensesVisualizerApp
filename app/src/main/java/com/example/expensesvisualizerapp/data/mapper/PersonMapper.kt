package com.example.expensesvisualizerapp.data.mapper

import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.domain.entities.PersonEntity

fun List<Person>.toEntity(): List<PersonEntity> {
    return this.map {
        PersonEntity(
            id = it.id,
            name = it.name,
            age = it.age,
            position = it.position,
            budget =it.budget,
//            expenses = it.expenses
        )
    }
}
package com.example.expensesvisualizerapp.domain.usecases.person

import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.domain.repositories.PersonRepository
import com.example.expensesvisualizerapp.domain.usecases.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAllPeopleUseCase @Inject constructor(
    private val repository: PersonRepository,
    background: CoroutineDispatcher
): UseCase<List<PersonEntity>, Person>(background) {
        override suspend fun run(input: Person?): List<PersonEntity> {
            return repository.getAllPeople()
        }
}

package com.example.expensesvisualizerapp.domain.usecases.person

import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.domain.repositories.PersonRepository
import com.example.expensesvisualizerapp.domain.usecases.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(
    private val repository: PersonRepository,
    background: CoroutineDispatcher
): UseCase<PersonEntity, Long>(background) {
    override suspend fun run(input: Long?): PersonEntity {
        requireNotNull(input){ "Person Must not be null" }
        return repository.getPersonDetails(input).firstOrNull() ?: PersonEntity()
    }
}
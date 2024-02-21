package com.example.expensesvisualizerapp.domain.usecases.person

import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.domain.repositories.PersonRepository
import com.example.expensesvisualizerapp.domain.usecases.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AddExpenseToPersonUseCase @Inject constructor(
    private val repository: PersonRepository,
    background: CoroutineDispatcher
): UseCase<Unit, PersonEntity>(background) {
    override suspend fun run(input: PersonEntity?) {
        requireNotNull(input){ "Person Must not be null" }
        return repository.saveExpenses(input)
    }
}
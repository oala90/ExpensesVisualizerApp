package com.example.expensesvisualizerapp.di

import com.example.expensesvisualizerapp.domain.repositories.PersonRepository
import com.example.expensesvisualizerapp.domain.usecases.person.AddExpenseToPersonUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.DeletePersonUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.GetAllPeopleUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.GetPersonDetailsUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.InsertPersonUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.UpdatePersonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideGetAllPeopleUseCase(
        repository: PersonRepository,
        background: CoroutineDispatcher
    ) = GetAllPeopleUseCase(repository, background)

    @Singleton
    @Provides
    fun provideAddExpenseToPersonUseCase(
        repository: PersonRepository,
        background: CoroutineDispatcher
    ) = AddExpenseToPersonUseCase(repository, background)


    @Singleton
    @Provides
    fun provideInsertPersonUseCase(
        repository: PersonRepository,
        background: CoroutineDispatcher
    ) = InsertPersonUseCase(repository, background)

    @Singleton
    @Provides
    fun provideUpdatePersonUseCase(
        repository: PersonRepository,
        background: CoroutineDispatcher
    ) = UpdatePersonUseCase(repository, background)

    @Singleton
    @Provides
    fun provideDeletePersonUseCase(
        repository: PersonRepository,
        background: CoroutineDispatcher
    ) = DeletePersonUseCase(repository, background)

    @Singleton
    @Provides
    fun provideGetPersonDetailsUseCase(
        repository: PersonRepository,
        background: CoroutineDispatcher
    ) = GetPersonDetailsUseCase(repository, background)

}
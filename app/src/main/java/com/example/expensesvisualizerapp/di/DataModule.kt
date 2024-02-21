package com.example.expensesvisualizerapp.di

import com.example.expensesvisualizerapp.data.local.source.PersonLocalDataSourceImpl
import com.example.expensesvisualizerapp.data.repositories.PersonLocalDataSource
import com.example.expensesvisualizerapp.data.repositories.PersonRepositoryImpl
import com.example.expensesvisualizerapp.domain.repositories.PersonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun binPersonLocalDataSource(
        personLocalDataSourceImpl: PersonLocalDataSourceImpl
    ) : PersonLocalDataSource

    @Binds
    internal abstract fun bindPersonRepository(
        personRepositoryImpl: PersonRepositoryImpl
    ) : PersonRepository
}
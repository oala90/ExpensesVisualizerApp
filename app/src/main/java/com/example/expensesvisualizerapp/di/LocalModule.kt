package com.example.expensesvisualizerapp.di

import com.example.expensesvisualizerapp.data.dao.PersonDao
import com.example.expensesvisualizerapp.data.local.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun providePersonDao(appDatabase: AppDataBase): PersonDao = appDatabase.personDao()
}
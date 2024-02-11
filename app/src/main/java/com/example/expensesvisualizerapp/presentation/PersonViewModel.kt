package com.example.expensesvisualizerapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.domain.usecases.person.GetAllPeopleUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.InsertPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val insertPersonUseCase: InsertPersonUseCase,
    private val getAllPeopleUseCase: GetAllPeopleUseCase
): ViewModel() {

    private val _personObject = MutableStateFlow<Person?>(null)
    val personObject = _personObject.asStateFlow()

    private val _personList = MutableStateFlow<List<PersonEntity>?>(null)
    val personList = _personList.asStateFlow()

    val name = MutableStateFlow("")
    val age = MutableStateFlow(0)
    val position = MutableStateFlow("")
    val budget = MutableStateFlow(0)

    fun getAllPeople() = viewModelScope.launch(Dispatchers.Main) {
        getAllPeopleUseCase().fold(
            {
                _personList.value = it
            }, {
                _personList.value = null
            }
        )
    }

    fun insertPersonDetails(person: Person?) = viewModelScope.launch(Dispatchers.Main) {
        val personObject = Person(
            id = 0L,
            name = name.value,
            age = age.value,
            position = position.value,
            budget = budget.value,
//            expenses = emptyList()
        )

        insertPersonUseCase().fold(
            {
                _personObject.value = personObject
            }, {
                _personObject.value = null
            }
        )
    }
}
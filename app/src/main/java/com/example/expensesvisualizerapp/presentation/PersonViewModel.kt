package com.example.expensesvisualizerapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.domain.usecases.person.GetAllPeopleUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.InsertPersonUseCase
import com.example.expensesvisualizerapp.presentation.actions.PersonActions
import com.example.expensesvisualizerapp.presentation.form.PersonForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val insertPersonUseCase: InsertPersonUseCase,
    private val getAllPeopleUseCase: GetAllPeopleUseCase
) : ViewModel() {

    private val _personList = MutableStateFlow<List<PersonEntity>?>(null)
    val personList = _personList.asStateFlow()

    private val _personForm = MutableStateFlow(PersonForm())
    val personForm = _personForm.asStateFlow()

    fun onFieldChange(action: PersonActions) {
        when (action) {
            is PersonActions.OnNameChanged -> {
                _personForm.update {
                    it.copy(name = action.value)
                }
            }

            is PersonActions.OnAgeChanged -> {
                _personForm.update {
                    it.copy(age = action.value)
                }
            }

            is PersonActions.OnPositionChanged -> {
                _personForm.update {
                    it.copy(position = action.value)
                }
            }

            is PersonActions.OnBudgetChanged -> {
                _personForm.update {
                    it.copy(budget = action.value)
                }
            }
        }
    }

    fun getAllPeople() = viewModelScope.launch(Dispatchers.Main) {
        getAllPeopleUseCase().fold(
            {
                _personList.value = it
            }, {
                _personList.value = null
            }
        )
    }

    fun insertPersonDetails() = viewModelScope.launch(Dispatchers.Main) {
        val personForm = _personForm.value
        val personObject = Person(
            name = personForm.name,
            age = personForm.age,
            position = personForm.position,
            budget = personForm.budget
        )

        insertPersonUseCase(input = personObject).fold()
    }
}
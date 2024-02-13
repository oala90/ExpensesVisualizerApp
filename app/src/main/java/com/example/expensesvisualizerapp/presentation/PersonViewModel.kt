package com.example.expensesvisualizerapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesvisualizerapp.data.dto.Person
import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.domain.usecases.person.DeletePersonUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.GetAllPeopleUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.InsertPersonUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.UpdatePersonUseCase
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
    private val getAllPeopleUseCase: GetAllPeopleUseCase,
    private val updatePersonUseCase: UpdatePersonUseCase,
    private val deletePersonUseCase: DeletePersonUseCase
) : ViewModel() {

    private val _personList = MutableStateFlow<List<PersonEntity>?>(null)
    val personList = _personList.asStateFlow()

    private val _personForm = MutableStateFlow(PersonForm())
    val personForm = _personForm.asStateFlow()

    fun setPersonForm(person: PersonEntity?) {
        person?.let {
            _personForm.value = PersonForm(
                name = it.name,
                age = it.age,
                position = it.position,
                budget = it.budget,
                expenses = it.expenses ?: emptyList()
            )
        }
    }

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

            is PersonActions.OnExpensesChanged -> {
                _personForm.update {
                    it.copy(expenses = action.value)
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

    fun onAddOrUpdateFun(onUpdate: Boolean) {
        if(onUpdate) {
            updatePersonDetails()
        } else {
            insertPersonDetails()
        }
    }

    private fun insertPersonDetails() = viewModelScope.launch(Dispatchers.Main) {
        val personForm = _personForm.value
        val personObject = PersonEntity(
            name = personForm.name,
            age = personForm.age,
            position = personForm.position,
            budget = personForm.budget,
            expenses = emptyList()
        )

        insertPersonUseCase(input = personObject).fold(
            {
//                getAllPeople()
            },
            {
                println("ERROR")
            }
        )
    }

    private fun updatePersonDetails() = viewModelScope.launch(Dispatchers.Main) {
        val personForm = _personForm.value
        val personObject = PersonEntity(
            id = personForm.id,
            name = personForm.name,
            age = personForm.age,
            position = personForm.position,
            budget = personForm.budget,
            expenses = personForm.expenses ?: emptyList()
        )

        updatePersonUseCase(input = personObject).fold(
            { getAllPeople() }, {}
        )
    }

    fun deletePersonDetails() = viewModelScope.launch(Dispatchers.Main) {
        val personForm = _personForm.value
        val personObject = PersonEntity(
            id = personForm.id,
            name = personForm.name,
            age = personForm.age,
            position = personForm.position,
            budget = personForm.budget,
            expenses = personForm.expenses
        )

        deletePersonUseCase(input = personObject).fold(
            { getAllPeople() }, {}
        )
    }
}
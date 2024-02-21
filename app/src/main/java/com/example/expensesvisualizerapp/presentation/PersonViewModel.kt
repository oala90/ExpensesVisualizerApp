package com.example.expensesvisualizerapp.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity
import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.domain.usecases.person.AddExpenseToPersonUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.DeletePersonUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.GetAllPeopleUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.GetPersonDetailsUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.InsertPersonUseCase
import com.example.expensesvisualizerapp.domain.usecases.person.UpdatePersonUseCase
import com.example.expensesvisualizerapp.presentation.actions.ExpensesActions
import com.example.expensesvisualizerapp.presentation.actions.PersonActions
import com.example.expensesvisualizerapp.presentation.form.ExpensesForm
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
    savedStateHandle: SavedStateHandle,
    private val insertPersonUseCase: InsertPersonUseCase,
    private val getAllPeopleUseCase: GetAllPeopleUseCase,
    private val updatePersonUseCase: UpdatePersonUseCase,
    private val deletePersonUseCase: DeletePersonUseCase,
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase,
    private val addExpenseToPerson: AddExpenseToPersonUseCase
) : ViewModel() {

//    private val personId = savedStateHandle.get<Long>("personId")

    private val _personList = MutableStateFlow<List<PersonEntity>?>(null)
    val personList = _personList.asStateFlow()

    private val _personForm = MutableStateFlow(PersonForm())
    val personForm = _personForm.asStateFlow()

    private val _expenseForm = MutableStateFlow(ExpensesForm())
    val expenseForm = _expenseForm.asStateFlow()

    private val _selectedPerson = MutableStateFlow(PersonEntity())
    val selectedPerson = _selectedPerson.asStateFlow()

    private val _isUpdate = MutableStateFlow(false)
    val isUpdate = _isUpdate.asStateFlow()

    private val _personId = MutableStateFlow<Long?>(0L)
    val personId = _personId.asStateFlow()
    fun setSelectedPerson(person: PersonEntity) {
        _selectedPerson.value = person
    }

    fun setPersonForm(isUpdate: Boolean, person: PersonEntity) {
        if (!isUpdate) {
            _personForm.value = PersonForm()
        } else {
            _personForm.value = PersonForm(
                id = person.id,
                name = person.name,
                age = person.age,
                position = person.position,
                budget = person.budget,
                expenses = person.expenses
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

    fun onFieldExpenseChange(action: ExpensesActions) {
        when (action) {
            is ExpensesActions.OnDescriptionChanged -> {
                _expenseForm.update {
                    it.copy(description = action.value)
                }
            }

            is ExpensesActions.OnAmountChanged -> {
                _expenseForm.update {
                    it.copy(amount = action.value)
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
            expenses = personForm.expenses
        )

        insertPersonUseCase(input = personObject).fold(
            {},
            {}
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
            expenses = personForm.expenses
        )

        updatePersonUseCase(input = personObject).fold(
            {},
            {}
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

    fun getPersonDetails(onUpdate: Boolean, personId: Long?) = viewModelScope.launch(Dispatchers.Main) {
        getPersonDetailsUseCase(personId).fold(
            {
                setPersonForm(onUpdate, it)
                _selectedPerson.value = it
            },
            {}
        )
    }

    fun addExpenseToPersonFunction(person: PersonEntity) = viewModelScope.launch(Dispatchers.Main) {
        val expenseForm = expenseForm.value
        val newExpense = ExpensesEntity(
            personId = person.id,
            description = expenseForm.description,
            amount = expenseForm.amount
        )
        val updatedExpenses = person.expenses.toMutableList()
        updatedExpenses.add(newExpense)
        val updatedPerson = person.copy(expenses = updatedExpenses)

        setSelectedPerson(updatedPerson)
        addExpenseToPerson(updatedPerson)
    }
}
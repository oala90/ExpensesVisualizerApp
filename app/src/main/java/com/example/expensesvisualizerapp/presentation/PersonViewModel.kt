package com.example.expensesvisualizerapp.presentation

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
import com.example.expensesvisualizerapp.presentation.statevalidation.ValidationState
import com.example.expensesvisualizerapp.presentation.statevalidation.ValidationState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle,
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

    private val _nameValidationState = MutableStateFlow<ValidationState>(Idle)
    val nameValidationState = _nameValidationState.asStateFlow()

    private val _ageValidationState = MutableStateFlow<ValidationState>(Idle)
    val ageValidationState = _ageValidationState.asStateFlow()

    private val _positionValidationState = MutableStateFlow<ValidationState>(Idle)
    val positionValidationState = _positionValidationState.asStateFlow()

    private val _budgetValidationState = MutableStateFlow<ValidationState>(Idle)
    val budgetValidationState = _budgetValidationState.asStateFlow()

    private val _descriptionExpenseValidationState = MutableStateFlow<ValidationState>(Idle)
    val descriptionExpenseValidationState = _descriptionExpenseValidationState.asStateFlow()

    private val _amountExpenseValidationState = MutableStateFlow<ValidationState>(Idle)
    val amountExpenseValidationState = _amountExpenseValidationState.asStateFlow()

    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled = _isButtonEnabled.asStateFlow()

    private val _isButtonExpenseEnabled = MutableStateFlow(false)
    val isButtonExpenseEnabled = _isButtonExpenseEnabled.asStateFlow()

    private val _isFormSubmitted = MutableStateFlow(false)
    val isFormSubmitted = _isFormSubmitted.asStateFlow()

    private val _isFormExpenseSubmitted = MutableStateFlow(false)
    val isFormExpenseSubmitted = _isFormExpenseSubmitted.asStateFlow()

    private val _personId = MutableStateFlow<Long?>(0L)
    val personId = _personId.asStateFlow()

    fun submitForm(flag: Boolean) {
        _isFormSubmitted.value = flag
    }

    fun submitExpenseForm(flag: Boolean) {
        _isFormExpenseSubmitted.value = flag
    }

    fun setSelectedPerson(person: PersonEntity) {
        _selectedPerson.value = person
    }

    fun validateName(name: String) {
        _nameValidationState.value = if (name.isEmpty()) {
            Invalid("Name cannot be empty.")
        } else if(!name.matches(Regex("^[A-Za-z]+(?: [A-Za-z]+)*\$"))) {
            Invalid("Name is invalid")
        } else {
            Valid
        }
        addUpdatePersonButtonEnabledState()
    }

    fun validateAge(age: String) {
        _ageValidationState.value = if (age.isEmpty()) {
            Invalid("Age cannot be empty")
        } else if (!age.matches(Regex("^[1-9][0-9]*\$"))) {
            Invalid("Age must be a valid number greater than 0")
        } else {
            Valid
        }
        addUpdatePersonButtonEnabledState()
    }

    fun validatePosition(position: String) {
        _positionValidationState.value = if (position.isEmpty()) {
            Invalid("Position cannot be empty")
        } else if(!position.matches(Regex("^[A-Za-z]+(?: [A-Za-z]+)*\$"))) {
            Invalid("Position is invalid")
        } else {
            Valid
        }
        addUpdatePersonButtonEnabledState()
    }

    fun validateBudget(budget: String) {
        _budgetValidationState.value = if (budget.isEmpty()) {
            Invalid("Budget cannot be empty")
        } else if (!budget.matches(Regex( "^[1-9][0-9]*(\\.[0-9]+)?\$"))) {
            Invalid("Budget must be a valid number greater than 0")
        } else {
            Valid
        }
        addUpdatePersonButtonEnabledState()
    }

    private fun addUpdatePersonButtonEnabledState() {
        _isButtonEnabled.value = (
                _nameValidationState.value is Valid &&
                _ageValidationState.value is Valid &&
                _positionValidationState.value is Valid &&
                _budgetValidationState.value is Valid
        )
    }

    private fun resetButtonEnabledState(isUpdate: Boolean) {
        _isButtonEnabled.value = isUpdate
    }

    fun validateDescription(description: String) {
        _descriptionExpenseValidationState.value = if (description.isEmpty()) {
            Invalid("Description cannot be empty")
        } else if(!description.matches(Regex("^[A-Za-z]+(?: [A-Za-z]+)*\$"))) {
            Invalid("Description is invalid")
        } else {
            Valid
        }
        addExpenseButtonEnabledState()
    }
    fun validateAmount(amount: String) {
        _amountExpenseValidationState.value = if (amount.isEmpty()) {
            Invalid("Amount cannot be empty")
        } else if(!amount.matches(Regex( "^[1-9][0-9]*(\\.[0-9]+)?\$"))) {
            Invalid("Amount must be a valid number greater than 0")
        } else {
            Valid
        }
        addExpenseButtonEnabledState()
    }

    private fun addExpenseButtonEnabledState() {
        _isButtonExpenseEnabled.value = (
                _descriptionExpenseValidationState.value is Valid &&
                        _amountExpenseValidationState.value is Valid
                )
    }

    fun resetValidationStates() {
        validateName("")
        validateAge("")
        validatePosition("")
        validateBudget("")
    }

    fun resetExpenseValidationStates() {
        validateDescription("")
        validateAmount("")
    }

    fun setPersonForm(isUpdate: Boolean, person: PersonEntity) {
        if (!isUpdate) {
            _personForm.value = PersonForm()
        } else {
            _personForm.value = PersonForm(
                id = person.id,
                name = person.name,
                age = person.age.toString(),
                position = person.position,
                budget = person.budget.toString(),
                expenses = person.expenses
            )
        }
    }

    fun setExpensesPersonForm(isUpdate: Boolean, expense: ExpensesEntity) {
        if (!isUpdate) {
            _expenseForm.value = ExpensesForm()
        } else {
            _expenseForm.value = ExpensesForm(
                id = expense.id,
                personId = expense.personId,
                description = expense.description,
                amount = expense.amount.toString()
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
            age = personForm.age.toInt(),
            position = personForm.position,
            budget = personForm.budget.toDouble(),
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
            age = personForm.age.toInt(),
            position = personForm.position,
            budget = personForm.budget.toDouble(),
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
            age = personForm.age.toInt(),
            position = personForm.position,
            budget = personForm.budget.toDouble(),
            expenses = personForm.expenses
        )

        deletePersonUseCase(input = personObject).fold(
            {}, {}
        )
    }

    fun getPersonDetails(onUpdate: Boolean, personId: Long?) = viewModelScope.launch(Dispatchers.Main) {
        getPersonDetailsUseCase(personId).fold(
            {
                setPersonForm(onUpdate, it)
                _selectedPerson.value = it
                if (onUpdate) {
                    validateName(it.name)
                    validateAge(it.age.toString())
                    validatePosition(it.position)
                    validateBudget(it.budget.toString())
                    resetButtonEnabledState(true)
                } else {
                    resetValidationStates()
                    resetButtonEnabledState(false)
                }
            },
            {}
        )
    }

    fun getExpenseOfPerson(onUpdate: Boolean, expenseId: String?) {
        val selectedExpense = _selectedPerson.value.expenses.find { it.id == expenseId }
        setExpensesPersonForm(onUpdate, selectedExpense ?: ExpensesEntity())
    }

    private fun generateUUID(): String {
        return UUID.randomUUID().toString()
    }
    fun addExpenseToPersonFunction(person: PersonEntity) = viewModelScope.launch(Dispatchers.Main) {
        val expenseForm = expenseForm.value
        val newExpenseId = generateUUID()
        val newExpense = ExpensesEntity(
            id = newExpenseId,
            personId = person.id,
            description = expenseForm.description,
            amount = expenseForm.amount.toDouble()
        )
        val updatedExpenses = person.expenses.toMutableList()
        updatedExpenses.add(newExpense)
        val updatedPerson = person.copy(expenses = updatedExpenses)

        setSelectedPerson(updatedPerson)
        addExpenseToPerson(updatedPerson)
    }

    fun updateExpenseInPersonFunction(person: PersonEntity) = viewModelScope.launch(Dispatchers.Main) {
        val expenseForm = expenseForm.value
        val updatedExpenses = person.expenses.toMutableList()
        val updatedExpense = ExpensesEntity(
            id = expenseForm.id,
            personId = expenseForm.personId,
            description = expenseForm.description,
            amount = expenseForm.amount.toDouble()
        )
        val index = updatedExpenses.indexOfFirst { it.id == updatedExpense.id }
        updatedExpenses[index] = updatedExpense
        val updatedPerson = person.copy(expenses = updatedExpenses)
        setSelectedPerson(updatedPerson)
        updatePersonUseCase(updatedPerson)
    }
}
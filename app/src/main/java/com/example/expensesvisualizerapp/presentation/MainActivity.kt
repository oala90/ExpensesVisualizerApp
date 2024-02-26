package com.example.expensesvisualizerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity
import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.presentation.ui.theme.ExpensesVisualizerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val myViewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExpensesVisualizerAppTheme {
                        val navController = rememberNavController()

                        val personList by myViewModel.personList.collectAsState()
                        NavHost(
                            navController = navController,
                            startDestination = "PersonListView",
                        ) {

                            composable("PersonListView") {
                                LaunchedEffect(personList) {
                                    myViewModel.getAllPeople()
                                }

                                PersonListView(
                                    onViewList = personList ?: emptyList(),
                                    openPersonActivity = { person, isUpdate ->

                                        navController.navigate("PersonDetailsView/$isUpdate/${person?.id}")
                                    }
                                )
                            }

                            composable(
                                route = "PersonDetailsView/{isUpdate}/{personId}",
                                arguments = listOf(
                                    navArgument("isUpdate") { type = NavType.BoolType } ,
                                    navArgument("personId") { type = NavType.LongType }
                                )
                            ) {
                                navBackStackEntry ->
                                val isUpdate = navBackStackEntry.arguments?.getBoolean("isUpdate") ?: false
                                val personId = navBackStackEntry.arguments?.getLong("personId")

                                LaunchedEffect(personList) {
                                    myViewModel.submitForm(false)
                                    if(isUpdate) {
                                        myViewModel.getPersonDetails(true, personId)
                                    } else {
                                        myViewModel.setPersonForm(false, PersonEntity())
                                        myViewModel.resetValidationStates()
                                    }
                                }


                                val personFormState by myViewModel.personForm.collectAsState()
                                val ageValidation by myViewModel.ageValidationState.collectAsState()
                                val budgetValidation by myViewModel.budgetValidationState.collectAsState()
                                val positionValidation by myViewModel.positionValidationState.collectAsState()
                                val nameValidation by myViewModel.nameValidationState.collectAsState()
                                val isButtonEnabled by myViewModel.isButtonEnabled.collectAsState()
                                val isFormSubmitted by myViewModel.isFormSubmitted.collectAsState()

                                PersonDetailsView(
                                    onNameChanged = myViewModel::onFieldChange,
                                    currentName = personFormState.name,
                                    validateName = nameValidation,
                                    onValidateName = myViewModel::validateName,
                                    onAgeChanged = myViewModel::onFieldChange,
                                    ageChanged = personFormState.age,
                                    validateAge = ageValidation,
                                    onValidateAge = myViewModel::validateAge,
                                    onPositionChanged = myViewModel::onFieldChange,
                                    positionChanged = personFormState.position,
                                    validatePosition = positionValidation,
                                    onValidatePosition = myViewModel::validatePosition,
                                    onBudgetChanged = myViewModel::onFieldChange,
                                    budgetChanged = personFormState.budget,
                                    validateBudget = budgetValidation,
                                    onValidateBudget = myViewModel::validateBudget,
                                    onAddOrUpdate = {
                                        myViewModel.onAddOrUpdateFun(isUpdate)
                                        navController.popBackStack()
                                    },
                                    onDelete = {
                                        myViewModel.deletePersonDetails()
                                        navController.popBackStack()
                                    },
                                    onUpdate = isUpdate,
                                    onButtonEnabled = isButtonEnabled,
                                    onExpensesList = {
                                        myViewModel.setSelectedPerson(myViewModel.selectedPerson.value)
                                        navController.navigate("ExpensesListView/$personId")
                                    },
                                    expensesListFirstElement = personFormState.expenses,
                                    formSubmit = isFormSubmitted,
                                    isFormSubmit = {
                                        myViewModel.submitForm(it)
                                    }
                                )
                            }
                            composable(
                                route = "ExpensesListView/{personId}",
                                arguments = listOf(
                                    navArgument("personId") { type = NavType.LongType }
                                )
                            ) {
                                    navBackStackEntry->
                                val personId = navBackStackEntry.arguments?.getLong("personId")
                                val personFormState by myViewModel.personForm.collectAsState()

                                LaunchedEffect(Unit) {
                                    myViewModel.getPersonDetails(true, personId)
                                }

                                ExpensesListView(
                                    onViewList = personFormState.expenses,
                                    onAddExpense = { expenseId, isUpdate ->
                                        navController.navigate("ExpensesDetailsView/${personId}/${expenseId}/${isUpdate}")
                                    }
                                )
                            }

                            composable(
                                route = "ExpensesDetailsView/{personId}/{expenseId}/{isUpdate}",
                                arguments = listOf(
                                    navArgument("personId") { type = NavType.LongType },
                                    navArgument("expenseId") { type = NavType.StringType },
                                    navArgument("isUpdate") { type = NavType.BoolType }
                                )
                            ) {
                                    navBackStackEntry->
                                val personId = navBackStackEntry.arguments?.getLong("personId") ?: 0L
                                val expenseId = navBackStackEntry.arguments?.getString("expenseId")
                                val isUpdate = navBackStackEntry.arguments?.getBoolean("isUpdate") ?: false

                                LaunchedEffect(Unit) {
                                    if(isUpdate) {
                                        myViewModel.getPersonDetails(true, personId)
                                        myViewModel.getExpenseOfPerson(true, expenseId)
                                    } else {
                                        myViewModel.setExpensesPersonForm(false, ExpensesEntity())
                                    }
                                }

                                val expenseForm by myViewModel.expenseForm.collectAsState()
                                val descriptionValidation by myViewModel.descriptionExpenseValidationState.collectAsState()
                                val amountValidation by myViewModel.amountExpenseValidationState.collectAsState()
                                val isButtonExpenseEnabled by myViewModel.isButtonExpenseEnabled.collectAsState()


                                ExpensesDetailsView(
                                    onDescriptionChanged = myViewModel::onFieldExpenseChange,
                                    currentDescription = expenseForm.description,
                                    validateDescription = descriptionValidation,
                                    onValidateDescription = myViewModel::validateDescription,
                                    onAmountChanged = myViewModel::onFieldExpenseChange,
                                    currentAmount = expenseForm.amount,
                                    validateAmount = amountValidation,
                                    onValidateAmount = myViewModel::validateAmount,
                                    onButtonEnabled = isButtonExpenseEnabled,
                                    addNewExpenses = {
                                        if(!isUpdate) {
                                            myViewModel.addExpenseToPersonFunction(myViewModel.selectedPerson.value)
                                        } else {
                                            myViewModel.updateExpenseInPersonFunction(myViewModel.selectedPerson.value)
                                        }
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

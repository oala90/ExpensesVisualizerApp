package com.example.expensesvisualizerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.presentation.ui.theme.ExpensesVisualizerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val myViewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                            if(isUpdate) {
                                myViewModel.getPersonDetails(true, personId)
                            } else {
                                myViewModel.setPersonForm(false, PersonEntity())
                            }

                        }

                        val personFormState by myViewModel.personForm.collectAsState()

                        PersonDetailsView(
                            onNameChanged = myViewModel::onFieldChange,
                            currentName = personFormState.name ?: "",
                            onAgeChanged = myViewModel::onFieldChange,
                            ageChanged = personFormState.age ?: 0,
                            onPositionChanged = myViewModel::onFieldChange,
                            positionChanged = personFormState.position ?: "",
                            onBudgetChanged = myViewModel::onFieldChange,
                            budgetChanged = personFormState.budget ?: 0,
                            onAddOrUpdate = {
                                myViewModel.onAddOrUpdateFun(isUpdate)
                                navController.popBackStack()

                            },
                            onDelete = {
                                myViewModel.deletePersonDetails()
                                navController.popBackStack()
                            },
                            onUpdate = isUpdate,
                            onExpensesList = {
                                myViewModel.setSelectedPerson(myViewModel.selectedPerson.value)
                                navController.navigate("ExpensesListView/$personId")
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
                            onAddExpense = {
                                navController.navigate("ExpensesDetailsView/${personId}")

                            }
                        )
                    }

                    composable(
                        route = "ExpensesDetailsView/{personId}",
                        arguments = listOf(
                            navArgument("personId") { type = NavType.LongType }
                        )
                    ) {
                        navBackStackEntry->
                        val personId = navBackStackEntry.arguments?.getLong("personId")
                        LaunchedEffect(Unit) {
                            myViewModel.getPersonDetails(true, personId)
                        }

                        val expenseForm by myViewModel.expenseForm.collectAsState()


                        ExpensesDetailsView(
                            onDescriptionChanged = myViewModel::onFieldExpenseChange,
                            currentDescription = expenseForm.description,
                            onAmountChanged = myViewModel::onFieldExpenseChange,
                            currentAmount = expenseForm.amount,
                            addNewExpenses = {
                                myViewModel.addExpenseToPersonFunction(myViewModel.selectedPerson.value)
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

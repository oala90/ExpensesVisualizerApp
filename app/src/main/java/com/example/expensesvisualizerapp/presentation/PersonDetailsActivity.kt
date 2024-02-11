package com.example.expensesvisualizerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.expensesvisualizerapp.presentation.ui.theme.ExpensesVisualizerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailsActivity : ComponentActivity() {

    private val myViewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExpensesVisualizerAppTheme {
                // A surface container using the 'background' color from the theme
                val name = myViewModel.name.collectAsState()
                val age = myViewModel.age.collectAsState()
                val position = myViewModel.position.collectAsState()
                val budget = myViewModel.budget.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonDetailsView(
                        onNameChanged = {
                            myViewModel.name.value = it
                                        },
                        currentName = name.value,
                        onAgeChanged = {
                            myViewModel.age.value = it
                        },
                        ageChanged = age.value,
                        onPositionChanged = {
                            myViewModel.position.value = it
                        },
                        positionChanged = position.value,
                        onBudgetChanged = {
                            myViewModel.budget.value = it
                        },
                        budgetChanged = budget.value,
                        onAddOrUpdate = {
                            myViewModel.insertPersonDetails(
                                null
                            )
                        }
                    )
                }
            }
        }
    }
}
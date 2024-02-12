package com.example.expensesvisualizerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

                val personForm by myViewModel.personForm.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonDetailsView(
                        onNameChanged = myViewModel::onFieldChange,
                        currentName =personForm.name,
                        onAgeChanged = myViewModel::onFieldChange,
                        ageChanged = personForm.age,
                        onPositionChanged = myViewModel::onFieldChange,
                        positionChanged = personForm.position,
                        onBudgetChanged = myViewModel::onFieldChange,
                        budgetChanged = personForm.budget,
                        onAddOrUpdate = myViewModel::insertPersonDetails
                    )
                }
            }
        }
    }
}
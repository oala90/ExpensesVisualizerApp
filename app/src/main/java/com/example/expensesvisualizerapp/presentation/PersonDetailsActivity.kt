package com.example.expensesvisualizerapp.presentation

import android.app.Activity
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
import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.presentation.ui.theme.ExpensesVisualizerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailsActivity : ComponentActivity() {

    private val myViewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onUpdate = intent.getBooleanExtra("onUpdate", false)
        val person = intent.getParcelableExtra<PersonEntity>("personEntity")

        myViewModel.setPersonForm(person)
        setContent {
            ExpensesVisualizerAppTheme {

                val personForm by myViewModel.personForm.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonDetailsView(
                        onNameChanged = myViewModel::onFieldChange,
                        currentName = personForm.name ?: "",
                        onAgeChanged = myViewModel::onFieldChange,
                        ageChanged = personForm.age,
                        onPositionChanged = myViewModel::onFieldChange,
                        positionChanged = personForm.position ?: "",
                        onBudgetChanged = myViewModel::onFieldChange,
                        budgetChanged = personForm.budget,
                        onAddOrUpdate = {
                            myViewModel.onAddOrUpdateFun(onUpdate)
                            setResult(Activity.RESULT_OK)
                            finish()
                        },
                        onUpdate = onUpdate
                    )
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Finish the activity to navigate back to the previous activity in the activity stack
        finish()
    }
}
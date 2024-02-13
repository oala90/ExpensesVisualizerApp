package com.example.expensesvisualizerapp.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.expensesvisualizerapp.domain.entities.PersonEntity
import com.example.expensesvisualizerapp.presentation.ui.theme.ExpensesVisualizerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val myViewModel: PersonViewModel by viewModels()
    private val ADD_PERSON_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpensesVisualizerAppTheme {
                // A surface container using the 'background' color from the theme
                myViewModel.getAllPeople()
                val personList = myViewModel.personList.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonListView(
                        onViewList = personList.value ?: emptyList(),
                        openPersonActivity = { person, onUpdate ->
                            navigateToPersonDetailsActivity(person, onUpdate)
                        }
                    )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PERSON_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Refresh UI here, for example, by re-fetching the list of people
            myViewModel.getAllPeople()
        }
    }
    private fun navigateToPersonDetailsActivity(person: PersonEntity?, onUpdate: Boolean) {
        val intent = Intent(this, PersonDetailsActivity::class.java).apply {
            putExtra("personEntity", person)
            putExtra("onUpdate", onUpdate)
        }
        startActivityForResult(intent, ADD_PERSON_REQUEST_CODE)
    }
}

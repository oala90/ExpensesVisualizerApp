package com.example.expensesvisualizerapp.presentation

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
import com.example.expensesvisualizerapp.presentation.ui.theme.ExpensesVisualizerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val myViewModel: PersonViewModel by viewModels()

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
                        openPersonActivity = {
                            navigateToPersonDetailsActivity()
                        }
                    )
                }
            }
        }
    }

    private fun navigateToPersonDetailsActivity() {
        val intent = Intent(this, PersonDetailsActivity::class.java)
        startActivity(intent)
    }
}

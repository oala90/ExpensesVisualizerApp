package com.example.expensesvisualizerapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expensesvisualizerapp.domain.entities.PersonEntity

@Composable
fun PersonListView(
    onViewList: List<PersonEntity>,
    openPersonActivity: (PersonEntity?,Boolean) -> Unit,
) {
    Column {
        if (onViewList.isEmpty()) {
            Text(
                text = "No persons available",
                modifier = Modifier.padding(16.dp) // Add padding for better visibility
            )
        } else {
            LazyColumn {
                items(onViewList) { person ->
                    Text(text = person.name!!, Modifier.clickable {
                        openPersonActivity(person, true)
                    })
                }
            }
        }

        Button(
            onClick = { openPersonActivity(null, false) }
        ) {
            Text(text = "Add new person registration")
        }
    }
}

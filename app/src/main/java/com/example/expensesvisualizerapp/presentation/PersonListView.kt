package com.example.expensesvisualizerapp.presentation

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
    openPersonActivity: () -> Unit,
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
                    Text(text = person.name)
                }
            }
        }

        Button(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp, vertical = 8.dp) // Adjust padding for the Button
//            .height(10.dp) // Set the height of the Button
//            .width(200.dp), // Set the width of the Button
            onClick = { openPersonActivity() }
        ) {
            Text(text = "Add new person registration")
        }
    }
}

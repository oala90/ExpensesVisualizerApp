package com.example.expensesvisualizerapp.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.expensesvisualizerapp.domain.entities.PersonEntity

@Composable
fun PersonListView(
    onViewList: List<PersonEntity>,
    openPersonActivity: () -> Unit,
) {

    LazyColumn {
        items(onViewList) {
            Text(text = it.name)
        }
    }

    Button(
        onClick = { openPersonActivity() }
    ) {
        Text(text = "Add new person registration")
    }
}

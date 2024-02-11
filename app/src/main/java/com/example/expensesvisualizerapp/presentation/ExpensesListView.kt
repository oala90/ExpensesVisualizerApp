package com.example.expensesvisualizerapp.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ExpensesListView() {

    LazyColumn {

    }

    Button(
        onClick = { /*TODO*/ }
    ) {
        Text(text = "Add a new expense")
    }
}
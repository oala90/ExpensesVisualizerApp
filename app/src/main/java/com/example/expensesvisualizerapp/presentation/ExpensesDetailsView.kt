package com.example.expensesvisualizerapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExpensesDetailsView() {
    Column {
        TextField(
            value = "",
            onValueChange = {},
            label = {
                Text(text = "Description")
            }
        )

        TextField(
            value = "",
            onValueChange = {},
            label = {
                Text(text = "Amount")
            }
        )


        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Add new expense")
            }
        }
    }
}

@Preview
@Composable
fun ExpensesDetailsViewPreview() {
    ExpensesDetailsView()
}
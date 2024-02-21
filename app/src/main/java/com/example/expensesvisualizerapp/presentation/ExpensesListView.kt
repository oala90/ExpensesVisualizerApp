package com.example.expensesvisualizerapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity

@Composable
fun ExpensesListView(
    onViewList: List<ExpensesEntity>,
    onAddExpense: () -> Unit
) {

    Column {
        if (onViewList.isEmpty()) {
            Text(
                text = "No current expenses",
                modifier = Modifier.padding(16.dp) // Add padding for better visibility
            )
        } else {
            LazyColumn {
                items(onViewList) { expense ->
                    Text(text = "Description: ${expense.description} -> Amount: ${expense.amount}")
                }
            }
        }

        Button(
            onClick = {
                onAddExpense()
            }
        ) {
            Text(text = "Add a new expense")
        }
    }
}

@Preview
@Composable
fun ExpensesListViewPreview() {
    ExpensesListView(emptyList(), {})
}
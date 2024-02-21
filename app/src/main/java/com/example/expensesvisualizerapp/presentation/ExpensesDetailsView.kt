package com.example.expensesvisualizerapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.expensesvisualizerapp.presentation.actions.ExpensesActions

@Composable
fun ExpensesDetailsView(
    onDescriptionChanged: (ExpensesActions.OnDescriptionChanged) -> Unit,
    currentDescription: String,
    onAmountChanged: (ExpensesActions.OnAmountChanged) -> Unit,
    currentAmount: Int,
    addNewExpenses: () -> Unit
) {
    Column {
        TextField(
            value = currentDescription,
            onValueChange = {
                onDescriptionChanged(ExpensesActions.OnDescriptionChanged(it))
            },
            label = {
                Text(text = "Description")
            }
        )

        TextField(
            value = currentAmount.toString(),
            onValueChange = {
                onAmountChanged(ExpensesActions.OnAmountChanged(it.toInt()))
            },
            label = {
                Text(text = "Amount")
            }
        )

        Row {
            Button(onClick = { addNewExpenses() }) {
                Text(text = "Add new expense")
            }
        }
    }
}

@Preview
@Composable
fun ExpensesDetailsViewPreview() {
    ExpensesDetailsView(
        {},
        "",
        {},
        0,
        {})
}
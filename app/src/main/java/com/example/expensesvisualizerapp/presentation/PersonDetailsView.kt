package com.example.expensesvisualizerapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PersonDetailsView(
    onNameChanged: (String) -> Unit,
    currentName: String,
    onAgeChanged: (Int) -> Unit,
    ageChanged: Int,
    onPositionChanged: (String) -> Unit,
    positionChanged: String,
    onBudgetChanged: (Int) -> Unit,
    budgetChanged: Int,
    onAddOrUpdate: () -> Unit
) {
    Column (modifier = Modifier
        .fillMaxWidth()
    ) {
        TextField(
            value = currentName,
            onValueChange = {
                onNameChanged(it)
            },
            label = {
                Text(text = "Full Name")
            }
        )

        TextField(
            value = ageChanged.toString(),
            onValueChange = {
                onAgeChanged(it.toInt())
            },
            label = {
                Text(text = "Age")
            }
        )

        TextField(
            value = positionChanged,
            onValueChange = {
                onPositionChanged(it)
            },
            label = {
                Text(text = "Position")
            }
        )

        TextField(
            value = budgetChanged.toString(),
            onValueChange = {
                onBudgetChanged(it.toInt())
            },
            label = {
                Text(text = "Budget")
            }
        )

        Text(
            text = "Expenses",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
        )

        Row {
            Button(
                onClick = {
                    onAddOrUpdate
                }
            ) {
                Text(text = "Update")
            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Delete")
            }
        }
    }
}

@Preview
@Composable
fun PersonDetailsViewPreview() {
    PersonDetailsView(
        {},
        "",
        {},
        0,
        {},
        "",
        {},
        0,
        {}
    )
}
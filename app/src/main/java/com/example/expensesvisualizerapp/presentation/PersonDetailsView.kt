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
import androidx.compose.ui.unit.sp
import com.example.expensesvisualizerapp.presentation.actions.PersonActions
import kotlinx.coroutines.Job

@Composable
fun PersonDetailsView(
    onNameChanged: (PersonActions.OnNameChanged) -> Unit,
    currentName: String,
    onAgeChanged: (PersonActions.OnAgeChanged) -> Unit,
    ageChanged: Int,
    onPositionChanged: (PersonActions.OnPositionChanged) -> Unit,
    positionChanged: String,
    onBudgetChanged: (PersonActions.OnBudgetChanged) -> Unit,
    budgetChanged: Int,
    onAddOrUpdate: () -> Unit,
    onUpdate: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = currentName,
            onValueChange = {
                onNameChanged(PersonActions.OnNameChanged(it))
            },
            label = {
                Text(text = "Full Name")
            }
        )

        TextField(
            value = ageChanged.toString(),
            onValueChange = {
                onAgeChanged(PersonActions.OnAgeChanged(it.toInt()))
            },
            label = {
                Text(text = "Age")
            }
        )

        TextField(
            value = positionChanged,
            onValueChange = {
                onPositionChanged(PersonActions.OnPositionChanged(it))
            },
            label = {
                Text(text = "Position")
            }
        )

        TextField(
            value = budgetChanged.toString(),
            onValueChange = {
                onBudgetChanged(PersonActions.OnBudgetChanged(it.toInt()))
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
                    onAddOrUpdate()
                }
            ) {
                Text(text = if (onUpdate) "Update" else "Add")
            }

            if(onUpdate) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Delete")
                }
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
        {},
        true
    )
}
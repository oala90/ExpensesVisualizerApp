package com.example.expensesvisualizerapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity

@Composable
fun ExpensesListView(
    onViewList: List<ExpensesEntity>,
    onAddExpense: (String,Boolean) -> Unit
) {

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        if (onViewList.isEmpty()) {
            Text(
                text = "No current expenses",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(16.dp)
            )
        } else {
            LazyColumn (
                modifier = Modifier
                    .background(Color.White)
            ){
                items(onViewList) { expense ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Description: ${expense.description} -> Amount: ${expense.amount}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.small)
                                .clickable { onAddExpense(expense.id, true) },
                            fontSize = 16.sp)
                    }
                    Spacer(
                        modifier = Modifier
                            .height(4.dp)
                    )
                }
            }
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = {
                    onAddExpense("0", false)
                },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = "Add a new expense")
            }
        }
    }
}

@Preview
@Composable
fun ExpensesListViewPreview() {
    ExpensesListView(emptyList()) { _, _ -> }
}
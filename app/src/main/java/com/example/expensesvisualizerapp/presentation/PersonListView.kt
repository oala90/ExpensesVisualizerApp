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
import com.example.expensesvisualizerapp.domain.entities.PersonEntity

@Composable
fun PersonListView(
    onViewList: List<PersonEntity>,
    openPersonActivity: (PersonEntity?,Boolean) -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        if (onViewList.isEmpty()) {
            Text(
                text = "No persons available",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .background(Color.White)
            ) {
                items(onViewList) { person ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                openPersonActivity(person, true)
                            }
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "id: ${person.id} Name: ${person.name}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .clickable {
                                    openPersonActivity(person, true)
                                 }
                                .background(color = MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.small),
                            fontSize = 16.sp
                        )
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
                onClick = { openPersonActivity(PersonEntity(), false) },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = "Add new person registration")
            }
        }
    }
}

@Preview
@Composable
fun PersonListViewPreview() {
    PersonListView(emptyList()) {
            _, _ ->
    }
}

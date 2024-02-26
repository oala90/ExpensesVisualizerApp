package com.example.expensesvisualizerapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesvisualizerapp.domain.entities.ExpensesEntity
import com.example.expensesvisualizerapp.presentation.actions.PersonActions
import com.example.expensesvisualizerapp.presentation.statevalidation.ValidationState
import com.example.expensesvisualizerapp.presentation.visualtransformation.DigitLimitTransformation

@Composable
fun PersonDetailsView(
    onNameChanged: (PersonActions.OnNameChanged) -> Unit,
    currentName: String,
    validateName: ValidationState,
    onValidateName: (String) -> Unit,
    onAgeChanged: (PersonActions.OnAgeChanged) -> Unit,
    ageChanged: String?,
    validateAge: ValidationState,
    onValidateAge: (String) -> Unit,
    onPositionChanged: (PersonActions.OnPositionChanged) -> Unit,
    positionChanged: String,
    validatePosition: ValidationState,
    onValidatePosition: (String) -> Unit,
    onBudgetChanged: (PersonActions.OnBudgetChanged) -> Unit,
    budgetChanged: String?,
    validateBudget: ValidationState,
    onValidateBudget: (String) -> Unit,
    onAddOrUpdate: () -> Unit,
    onDelete: () -> Unit,
    onUpdate: Boolean,
    onButtonEnabled: Boolean,
    onExpensesList: () -> Unit,
    expensesListFirstElement: List<ExpensesEntity>,
    formSubmit: Boolean,
    isFormSubmit: (Boolean) -> Unit,
) {
    var isDialogVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
        TextField(
            value = currentName,
            onValueChange = {
                if(it.length <= 30) {
                    onNameChanged(PersonActions.OnNameChanged(it))
                    isFormSubmit(false)
                    onValidateName(it)
                }
            },
            maxLines = 1,
            visualTransformation = DigitLimitTransformation(30),
            modifier = Modifier
                .background
                    (
                        color = androidx.compose.ui.graphics.Color.White,
                        shape = MaterialTheme.shapes.small
                    )
                .padding(10.dp),
            label = {
                Text(text = "Full Name")
            }
        )

        if(formSubmit) {
            if (validateName is ValidationState.Invalid) {
                TextFieldError(textError = validateName.errorMessage)
            }
        }

        TextField(
            value = ageChanged.toString(),
            onValueChange = {
                if(it.length <= 3) {
                    onAgeChanged(PersonActions.OnAgeChanged(it))
                    isFormSubmit(false)
                    onValidateAge(it)
                }
            },
            maxLines = 1,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .background(
                    color = androidx.compose.ui.graphics.Color.White,
                    shape = MaterialTheme.shapes.small
                )
                .padding(10.dp),
            label = {
                Text(text = "Age")
            },
            visualTransformation = DigitLimitTransformation(3),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        if(formSubmit) {
            if (validateAge is ValidationState.Invalid) {
                TextFieldError(textError = validateAge.errorMessage)
            }
        }

        TextField(
            value = positionChanged,
            onValueChange = {
                if(it.length <= 15) {
                    onPositionChanged(PersonActions.OnPositionChanged(it))
                    isFormSubmit(false)
                    onValidatePosition(it)
                }
            },
            maxLines = 1,
            visualTransformation = DigitLimitTransformation(15),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .background(
                    color = androidx.compose.ui.graphics.Color.White,
                    shape = MaterialTheme.shapes.small
                )
                .padding(10.dp),
            label = {
                Text(text = "Position")
            }
        )

        if(formSubmit) {
            if (validatePosition is ValidationState.Invalid) {
                TextFieldError(textError = validatePosition.errorMessage)
            }
        }


        TextField(
            value = budgetChanged.toString(),
            onValueChange = {
                if(it.length <= 10) {
                    onBudgetChanged(PersonActions.OnBudgetChanged(it))
                    isFormSubmit(false)
                    onValidateBudget(it)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1,
            visualTransformation = DigitLimitTransformation(10),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .background(
                    color = androidx.compose.ui.graphics.Color.White,
                    shape = MaterialTheme.shapes.small
                )
                .padding(10.dp),
            label = {
                Text(text = "Budget")
            }
        )

        if(formSubmit) {
            if (validateBudget is ValidationState.Invalid) {
                TextFieldError(textError = validateBudget.errorMessage)
            }
        }

        if (onUpdate) {
            Text(
                text = if (expensesListFirstElement.isEmpty()) "Expenses" else expensesListFirstElement.firstOrNull()!!.description,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp,
                modifier = Modifier
                    .clickable {
                        onExpensesList()
                    }
                    .padding(vertical = 8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = {
                    isFormSubmit(true)
                    isDialogVisible = if(onButtonEnabled) {
                        onAddOrUpdate()
                        false
                    } else {
                        true
                    }
                },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = if (onUpdate) "Update" else "Add")
            }
            if(isDialogVisible) {
                ShowMessageDialog(
                    message = "Fields contain errors. Please fix them",
                    onDismiss = {
                        isDialogVisible = false
                    }
                )
            }

            if (onUpdate) {
                Button(
                    onClick = { onDelete() },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
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
        ValidationState.Idle,
        {},
        {},
        "",
        ValidationState.Idle,
        {_ ->},
        {},
        "",
        ValidationState.Idle,
        {},
        {},
        "",
        ValidationState.Idle,
        {_ ->},
        {},
        {},
        true,
        onButtonEnabled = false,
        onExpensesList = {},
        expensesListFirstElement = emptyList(),
        false,
        {}
    )
}
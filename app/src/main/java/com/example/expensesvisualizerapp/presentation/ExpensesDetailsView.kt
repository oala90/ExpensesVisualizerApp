package com.example.expensesvisualizerapp.presentation

import androidx.compose.foundation.background
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
import com.example.expensesvisualizerapp.presentation.actions.ExpensesActions
import com.example.expensesvisualizerapp.presentation.statevalidation.ValidationState
import com.example.expensesvisualizerapp.presentation.visualtransformation.DigitLimitTransformation

@Composable
fun ExpensesDetailsView(
    onDescriptionChanged: (ExpensesActions.OnDescriptionChanged) -> Unit,
    currentDescription: String,
    validateDescription: ValidationState,
    onValidateDescription: (String) -> Unit,
    onAmountChanged: (ExpensesActions.OnAmountChanged) -> Unit,
    currentAmount: String,
    validateAmount: ValidationState,
    onValidateAmount: (String) -> Unit,
    onButtonEnabled: Boolean,
    addNewExpenses: () -> Unit,
    formSubmit: Boolean,
    isFormSubmit: (Boolean) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var isDialogVisible by remember { mutableStateOf(false) }

        TextField(
            value = currentDescription,
            onValueChange = {
                if(it.length <= 15) {
                    onDescriptionChanged(ExpensesActions.OnDescriptionChanged(it))
                    isFormSubmit(false)
                    onValidateDescription(it)

                }
            },
            maxLines = 1,
            visualTransformation = DigitLimitTransformation(15),
            modifier = Modifier
                .background(color = androidx.compose.ui.graphics.Color.White, shape = MaterialTheme.shapes.small)
                .padding(10.dp),
            label = {
                Text(text = "Description")
            }
        )
        if (formSubmit && validateDescription is ValidationState.Invalid) {
            TextFieldError(textError = validateDescription.errorMessage)
        }

        TextField(
            value = currentAmount,
            onValueChange = {
                if(it.length <= 10) {
                    onAmountChanged(ExpensesActions.OnAmountChanged(it))
                    isFormSubmit(false)
                    onValidateAmount(it)

                }
            },
            maxLines = 1,
            visualTransformation = DigitLimitTransformation(10),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .background(color = androidx.compose.ui.graphics.Color.White, shape = MaterialTheme.shapes.small)
                .padding(10.dp),
            label = {
                Text(text = "Amount")
            }
        )
        if (formSubmit && validateAmount is ValidationState.Invalid) {
            TextFieldError(textError = validateAmount.errorMessage)
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = {
                    isFormSubmit(true)
                    isDialogVisible = if(onButtonEnabled) {
                        addNewExpenses()
                        false
                    } else {
                        true
                    }
                },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = "Add new expense")
            }

            if(isDialogVisible) {
                ShowMessageDialog(
                    message = "Text Fields contain errors. Please fix them",
                    onDismiss = {
                        isDialogVisible = false
                    }
                )
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
        ValidationState.Idle,
        {},
        {},
        "",
        ValidationState.Idle,
        {},
        false,
        {},
        false,
        {}
    )
}
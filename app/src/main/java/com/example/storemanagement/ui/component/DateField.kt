package com.example.storemanagement.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.format.DateTimeFormatter


@Composable
fun ReadonlyTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    error: MutableState<Boolean>,
    onClick: () -> Unit,
    label: String
) {
    Box {
        TextField(
            value = value,
            onValueChange =
            {
                onValueChange(it)

            },
            modifier = modifier.width(150.dp),
            readOnly = true,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            label = { Text(text = label) },
            trailingIcon = {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = ""
                    )
                }
            },
            shape = RoundedCornerShape(25.dp),
            isError = error.value

        )
    }
}

@Composable
fun MyDateField(
    textState: MutableState<TextFieldValue>,
    error: MutableState<Boolean>,
    label: String = "Date",
    modifier: Modifier = Modifier,

    ) {
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(dialogState,

        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }) {
        datepicker { date ->
            val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val formatedDate = date.format(pattern)
            textState.value = TextFieldValue(text = formatedDate)
            error.value = false
        }
    }

    Column {
        ReadonlyTextField(
            error = error,
            value = textState.value,
            onValueChange = { textState.value = it },
            onClick = {
                dialogState.show()
            },
            label = label,
            modifier = modifier
        )
        if (error.value) {
            Text(
                text = "Select EXP Date",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}


@Composable
fun MyTextDateField(
    textState: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier,

    ) {
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(dialogState,

        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }) {
        datepicker { date ->
            val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val formatedDate = date.format(pattern)
            textState.value = TextFieldValue(text = formatedDate)
        }
    }

    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = " ${textState.value.text}",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        IconButton(onClick = { dialogState.show() }) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = ""
            )
        }
    }
}


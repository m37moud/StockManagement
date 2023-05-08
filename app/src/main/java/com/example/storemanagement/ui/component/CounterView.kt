package com.example.storemanagement.ui.component

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.storemanagement.R

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    counter: MutableState<Int>,
    onMinus: () -> Unit,
    onAdd: () -> Unit
) {

    Row(
        modifier = modifier
//            .height(20.dp)
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Horizontal,
                enabled = true

            ), verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onMinus() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_navigate_before),
                contentDescription = "minus"
            )
        }
        TextField(
            modifier = Modifier.size(50.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
            ), textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = counter.value.toString(),
            onValueChange = {
                counter.value = it.toInt()
            })
        IconButton(onClick = { onAdd() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_navigate_next),
                contentDescription = "minus"
            )
        }
    }

}

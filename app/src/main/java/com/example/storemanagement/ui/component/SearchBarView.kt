package com.example.storemanagement.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchView(
    modifier: Modifier = Modifier, state: MutableState<TextFieldValue>,
    name: String,
    onpressEnterSearch: () -> Unit,
    onCloseSearch: () -> Unit,
) {
    Row(
        modifier = modifier
//                    .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            ), // this can change for contentPadding = PaddingValues(horizontal = 16.dp)
        verticalAlignment = Alignment.CenterVertically, // this modifier for column
//            horizontalArrangement = Arrangement.Center      // this can change for Arrangement.spacedBy()

    ) {
        OutlinedTextField(
            value = state.value,
            onValueChange = { value ->
                state.value = value
            },
            modifier = modifier.padding(end = 16.dp)
                .onKeyEvent {
                    if (it.key == Key.Enter) {
                        onpressEnterSearch()
                        true
                    } else {
                        false
                    }
                }.testTag("search"),
            textStyle = TextStyle(fontSize = 18.sp),
            placeholder = { Text("Search by name or ID") },
            label = { Text(text = "Search") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            },
            trailingIcon = {
                if (state.value != TextFieldValue("")) {
                    IconButton(
                        onClick = {
                            onCloseSearch()

                            state.value =
                                TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                        }
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,

            )




    }
}
package com.example.storemanagement.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun LoadingAlertDialog(
    modifier: Modifier = Modifier,
    openDialog: Boolean,
    closeDialog: () -> Unit,
//    addBook: (book: MainScreenState) -> Unit
) {
    if (openDialog) {
//        var title by remember { mutableStateOf(NO_VALUE) }
//        var author by remember { mutableStateOf(NO_VALUE) }
//        val focusRequester = FocusRequester()

        AlertDialog(
            onDismissRequest = { },
//            title = {
//
//                Text(
//                    text = ADD_BOOK
//                )
//            },
            text = {
                Row(
                    modifier = modifier
//                        .background(Color.LightGray)
                    ,
                   verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier, color = Color(255, 207, 64)
//                            .defaultMinSize(minWidth = 96.dp, minHeight = 96.dp),
                    )

                }
            },
            confirmButton = {
//                TextButton(
//                    onClick = {
//                        closeDialog()
//                        val book = Book(0, title, author)
//                        addBook(book)
//                    }
//                ) {
//                    Text(
//                        text = ADD
//                    )
//                }
            },
            dismissButton = {
//                TextButton(
//                    onClick = closeDialog
//                ) {
//                    Text(
//                        text = "DISMISS"
//                    )
//                }
            }
        )
    }
}
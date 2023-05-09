package com.example.storemanagement.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
 fun BottomAppBarComponent(onBack: (() -> Unit)? = null ) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 4.dp,
        cutoutShape = CircleShape
    ) {

//        // Leading icons should typically have a high content alpha
//        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
//            IconButton(
//                onClick = { onBack?.invoke() }) {
//                Icon(Icons.Filled.ArrowBack, contentDescription = null)
//            }
//
//        }
        // The actions should be at the end of the BottomAppBar. They use the default medium
        // content alpha provided by BottomAppBar
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { }) {
            Icon(Icons.Filled.Search, contentDescription = null)
        }

        IconButton(onClick = { }) {
            Icon(Icons.Filled.MoreVert, contentDescription = null)
        }
    }
}
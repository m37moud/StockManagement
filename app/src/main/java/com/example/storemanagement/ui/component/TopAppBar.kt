package com.example.storemanagement.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ActionTopAppbar(
    title: String,
    onBack: (() -> Unit)? = null,
    elevation: Dp = 0.dp,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(text = title, color = MaterialTheme.colors.onSurface)
        },
        elevation = elevation,
        backgroundColor = MaterialTheme.colors.surface,
//        backgroundColor = Color(255,207,64),
        contentColor = MaterialTheme.colors.onSecondary,
        navigationIcon = {
            IconButton(onClick = { onBack?.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface
                )
            }
        },
        actions = {
            actions()
        }
    )
}

package com.example.storemanagement.ui.feature.master.categories.add_category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.storemanagement.navigation.Screens
import com.example.storemanagement.ui.component.ActionTopAppbar
import com.example.storemanagement.ui.component.BottomAppBarComponent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddCategoryScreen(navController: NavController) {
    Scaffold(

        modifier = Modifier
            .fillMaxWidth()
            .background(SnackbarDefaults.backgroundColor),
        topBar = {
            ActionTopAppbar(
                title = "add Category",
                onBack = { navController.popBackStack() },
                elevation = 8.dp
            )
        },

        ) {
        MainContent(it.calculateBottomPadding(), onBack = { navController.popBackStack() })
    }
}


@ExperimentalMaterialApi
@Composable
private fun MainContent(bottomAppBarHeight: Dp, onBack: (() -> Unit)? = null) {
    // 🔥 Get BottomAppBar height to set correct bottom padding for LazyColumn
    Column(modifier = Modifier.fillMaxSize()) {
        val fullWidthModifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    PaddingValues(
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = bottomAppBarHeight + 8.dp
                    )
                )
        val textFieldValue = remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            modifier = fullWidthModifier,
            value = textFieldValue.value,
            onValueChange = { newValue ->
                textFieldValue.value = newValue
            },
            label = { Text("name") },
            placeholder = { Text("Enter Category name") },

//            colors = TextFieldDefaults.textFieldColors(
//                textColor = Color.White,
//                backgroundColor = Color(0xffFFA000),
//                placeholderColor = Color(0xffFFF176),
//                unfocusedLabelColor = Color(0xff795548),
//                focusedLabelColor = Color(0xff66BB6A),
//                errorLabelColor = Color(0xffFF1744),
//                unfocusedIndicatorColor = Color(0xffEF9A9A),
//                focusedIndicatorColor = Color(0xffFFA000)
//            )
        )
        Row(
            modifier = fullWidthModifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xffF57C00),
                    contentColor = Color.White
                )
            ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        modifier = Modifier.padding(start = 4.dp),
                        contentDescription = null
                    )
                    Text(text = "Save", modifier = Modifier.padding(end = 4.dp),)
            }

        }
    }

}
package com.example.storemanagement.ui.feature.master.products.add_product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import com.example.storemanagement.ui.component.ActionTopAppbar


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddProductScreen(navController: NavController) {
    Scaffold(

        modifier = Modifier
            .fillMaxWidth()
            .background(SnackbarDefaults.backgroundColor),
        topBar = {
            ActionTopAppbar(
                title = "add Product",
                onBack = { navController.popBackStack() },
//                elevation = 8.dp
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(horizontal = 6.dp),
                value = textFieldValue.value,
                onValueChange = { newValue ->
                    textFieldValue.value = newValue
                },
                label = { Text("file") },
                placeholder = { Text("chose ecxel file to Import") },

                )
            Button(
                onClick = {},
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xffF57C00),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    modifier = Modifier.padding(start = 4.dp),
                    contentDescription = null
                )
//                Text(text = "Save", modifier = Modifier.padding(end = 4.dp))
            }
        }
        Row(
            modifier = fullWidthModifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {},
                shape = RoundedCornerShape(15.dp),
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = Color(0xffF57C00),
//                    contentColor = Color.White
//                )
            ) {
                Text(text = "Import File", modifier = Modifier.padding(end = 4.dp))
            }

        }
        Divider(
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp),
            startIndent = 0.dp,
            thickness = 2.dp,
            color = MaterialTheme.colors.onSecondary,
        )
        Column(
            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                modifier = fullWidthModifier,
                value = textFieldValue.value,
                onValueChange = { newValue ->
                    textFieldValue.value = newValue
                },
                label = { Text("product name") },
                placeholder = { Text("Enter Product name") },
            )
            Spacer(modifier = Modifier.weight(1f))

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
                        imageVector = Icons.Filled.AddCircle,
                        modifier = Modifier.padding(start = 4.dp),
                        contentDescription = null
                    )
                    Text(text = "Save", modifier = Modifier.padding(end = 4.dp),)
                }

            }
        }

    }

}
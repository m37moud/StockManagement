package com.example.storemanagement.ui.feature.master.products.add_product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.storemanagement.ui.component.ActionTopAppbar
import com.example.storemanagement.ui.component.DropDownMenu
import com.example.storemanagement.ui.theme.StoreManagementTheme
import com.example.storemanagement.ui.theme.md_theme_light_background


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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)
    ) {
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
            if (!textFieldValue.value.text.isNotBlank()) {
                OutlinedButton(
                    onClick = {},
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xffF57C00),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Import File", modifier = Modifier.padding(end = 4.dp))
                }
            }

        }
        Divider(
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 12.dp),
            startIndent = 0.dp,
            thickness = 2.dp,
            color = MaterialTheme.colors.onSecondary,
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(.6f)
                    ,
                    value = textFieldValue.value,
                    onValueChange = { newValue ->
                        textFieldValue.value = newValue
                    },
                    label = { Text("ParCode") },
                    placeholder = { Text("Enter Product number") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                DropDownMenu(modifier = Modifier
                    .weight(.4f)
                    , name = "Category") {

                }


            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = textFieldValue.value,
                onValueChange = { newValue ->
                    textFieldValue.value = newValue
                },
                label = { Text("product name") },
                placeholder = { Text("Enter Product name") },
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = textFieldValue.value,
                    onValueChange = { newValue ->
                        textFieldValue.value = newValue
                    },
                    label = { Text("Unit") },
                    placeholder = { Text("Enter Unit") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = textFieldValue.value,
                    onValueChange = { newValue ->
                        textFieldValue.value = newValue
                    },
                    label = { Text("Pack") },
                    placeholder = { Text("00") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = textFieldValue.value,
                    onValueChange = { newValue ->
                        textFieldValue.value = newValue
                    },
                    label = { Text("Price") },
                    placeholder = { Text("00 EGP") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)

                )
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = textFieldValue.value,
                onValueChange = { newValue ->
                    textFieldValue.value = newValue
                },
                label = { Text("Item Description") },
                placeholder = { Text("Enter Item Description") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

            )

//            BasicTextField(
//                value = "absentNote",
//                onValueChange = {
//
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 4.dp)
//                    .weight(.5f)
//                    .clip(RectangleShape)
//                    .border(
//                        shape = RectangleShape,
//                        border = BorderStroke(2.dp, MaterialTheme.colors.onSurface)
//                    ),
//                textStyle = TextStyle(
//                    fontSize = 20.sp,
//                    fontStyle = FontStyle.Normal,
//                    fontFamily = FontFamily.Cursive,
//                    textAlign = TextAlign.Center
//                ),
//
//                )
            Spacer(modifier = Modifier.weight(1f))

            /**
             * add button
             */
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
                    Text(text = "Save", modifier = Modifier.padding(end = 4.dp))
                }

            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun PreviewAddProduct() {
    StoreManagementTheme {
        AddProductScreen(navController = rememberNavController())
    }

}
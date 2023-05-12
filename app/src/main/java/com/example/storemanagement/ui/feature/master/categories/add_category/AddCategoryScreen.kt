package com.example.storemanagement.ui.feature.master.categories.add_category

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.storemanagement.data.database.entity.CategoryEntity
import com.example.storemanagement.ui.component.ActionTopAppbar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddCategoryScreen(
    navController: NavController,
    addCategoryViewModel: AddCategoryViewModel = hiltViewModel()
) {
//
    val msg = addCategoryViewModel.message.collectAsState().value
    Log.d("AddCategoryScreen", "$msg")

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = msg) {
        if (msg.isNotBlank())
            scaffoldState.snackbarHostState.showSnackbar("$msg")
    }
    Scaffold(
        scaffoldState = scaffoldState,
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
        val context = LocalContext.current

        MainContent(it.calculateBottomPadding(),
            onAddCategoryClick = { category ->

                addCategoryViewModel.insertCategory(category)
//                Toast
//                    .makeText(context, msg, Toast.LENGTH_SHORT)
//                    .show()

            }, onBack = {
                navController.popBackStack()
            })
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterialApi
@Composable
private fun MainContent(
    bottomAppBarHeight: Dp,
    onAddCategoryClick: (CategoryEntity) -> Unit = {},
    onBack: (() -> Unit)? = null
) {

    val filePath = remember { mutableStateOf(TextFieldValue("")) }
    val categoryName = remember { mutableStateOf(TextFieldValue("")) }

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(horizontal = 6.dp),
                value = filePath.value,
                onValueChange = { newValue ->
                    filePath.value = newValue
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
                onClick = {
                    // TODO:  handle import file
                },
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
            val keyboardController = LocalSoftwareKeyboardController.current

            OutlinedTextField(
                modifier = fullWidthModifier,
                value = categoryName.value,
                onValueChange = { newValue ->
                    categoryName.value = newValue
                },
                label = { Text("category name") },
                placeholder = { Text("Enter Category name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ), keyboardActions = KeyboardActions(onDone = {

                    keyboardController?.hide()
                    CategoryEntity(categoryName = categoryName.value.text).apply {
                        onAddCategoryClick(
                            this
                        )
                    }


                })

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
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = fullWidthModifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {

                        CategoryEntity(categoryName = categoryName.value.text).apply {
                            onAddCategoryClick(
                                this
                            )
                        }

                    },
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
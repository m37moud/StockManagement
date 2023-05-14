package com.example.storemanagement.ui.feature.master.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.storemanagement.model.Categories
import com.example.storemanagement.navigation.Screens
import com.example.storemanagement.ui.component.ActionTopAppbar
import com.example.storemanagement.ui.component.BottomAppBarComponent

@ExperimentalMaterialApi
@Composable
fun ProductScreen(navController: NavController) {
    val listCategories = listOf(
        Categories(id = "1", name = "test 1 "),
        Categories(id = "2", name = "test 1"),
        Categories(id = "3", name = "test 1"),
        Categories(id = "4", name = "test 1"),
    )
    Scaffold(

        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        topBar = {
            ActionTopAppbar(
                title = "Products",
                onBack = {
                    navController.popBackStack()
                },
                elevation = 8.dp,
//                actions = {
//                    addCategories(onAddPress =
//                    { navController.navigate(Screens.AddCategoryScreen.route) })
//                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.AddProductScreen.route)
                },
                backgroundColor = Color(255,207,64 )// 0xffFFA000
            ) {
                Icon(
                    Icons.Filled.Add, tint = Color.White,
                    contentDescription = null
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBarComponent(onBack = {
                navController.popBackStack()
            })
        }
    ) {

        MainContent(
            it.calculateBottomPadding(),
            listCategories = listCategories
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun MainContent(bottomAppBarHeight: Dp, listCategories: List<Categories>) {
    // 🔥 Get BottomAppBar height to set correct bottom padding for LazyColumn
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color.LightGray.copy(alpha = .4f))
        ,
        contentPadding = PaddingValues(
            top = 8.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = bottomAppBarHeight + 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(listCategories) { category ->

            CategoryItem(categories = category)
        }
    }
}

@Composable
fun CategoryItem(modifier: Modifier = Modifier, categories: Categories) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = categories.id,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
//            color = Color.Black
        )
        Text(
            text = categories.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
//            color = Color.Black
        )
    }
}
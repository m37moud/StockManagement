package com.example.storemanagement.ui.feature.master.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.storemanagement.data.database.entity.CategoryEntity
import com.example.storemanagement.model.Categories
import com.example.storemanagement.navigation.Screens
import com.example.storemanagement.ui.component.ActionTopAppbar
import com.example.storemanagement.ui.component.BottomAppBarComponent
import com.example.storemanagement.ui.component.ErrorUI
import com.example.storemanagement.ui.component.LoadingScreen
import com.example.storemanagement.util.Lce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CategoriesScreen(
    navController: NavController,
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
) {
    val allCategories =
        categoriesViewModel.readAllCategories.collectAsState().value


    Scaffold(

        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        topBar = {
            ActionTopAppbar(
                title = "categories",
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
                    navController.navigate(Screens.AddCategoryScreen.route)
                },
                backgroundColor = Color(0xffFFA000)
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

        ContentScreen(it.calculateBottomPadding(), categoriesList = allCategories)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContentScreen(bottomAppBarHeight: Dp, categoriesList: Lce<List<CategoryEntity>>) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        if (categoriesList != null) {
//            if (categoriesList.isNotEmpty())
//                MainContent(
//                    bottomAppBarHeight,
//                    categoriesList = categoriesList
//
//                ) else {
//
//            }
//        }
        when (val state = categoriesList) {
//                    is LCE.LOADING -> LoadingUI()
            is Lce.Loading -> LoadingScreen()
            is Lce.Content -> {
                MainContent(
                    bottomAppBarHeight,
                    categoriesList = state.data

                )
            }

            is Lce.Error -> ErrorUI(state.message)
            else -> {}
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun MainContent(bottomAppBarHeight: Dp, categoriesList: List<CategoryEntity>) {
//    val list = categoriesList.collectAsState(initial = emptyList())
    // 🔥 Get BottomAppBar height to set correct bottom padding for LazyColumn
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = .4f)),
        contentPadding = PaddingValues(
            top = 8.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = bottomAppBarHeight + 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

//        items(list!!.value) { category ->
        items(categoriesList) { category ->

            CategoryItem(categories = category)
        }
    }
}

@Composable
fun CategoryItem(modifier: Modifier = Modifier, categories: CategoryEntity) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = categories.id.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
//            color = Color.Black
        )
        Text(
            text = categories.categoryName,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
//            color = Color.Black
        )
    }
}

@Composable
fun RowScope.addCategories(onAddPress: (() -> Unit)? = null) {
    IconButton(onClick = { onAddPress?.invoke() }) {
        Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "add category")
    }
}
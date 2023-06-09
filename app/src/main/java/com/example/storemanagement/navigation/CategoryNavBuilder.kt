package com.example.storemanagement.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.storemanagement.ui.feature.master.categories.CategoriesScreen
import com.example.storemanagement.ui.feature.master.categories.add_category.AddCategoryScreen
import com.example.storemanagement.ui.feature.master.products.ProductScreen

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.categories(navHostController: NavHostController) {
    navigation(startDestination = Screens.CategoriesScreen.route, route = CATEGORY) {
        composable(route = Screens.CategoriesScreen.route) {
            CategoriesScreen(navController = navHostController)
        }
        composable(route = Screens.AddCategoryScreen.route) {
            AddCategoryScreen(navController = navHostController)
        }
    }
}
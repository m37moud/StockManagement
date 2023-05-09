package com.example.storemanagement.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.storemanagement.ui.feature.master.categories.add_category.AddCategoryScreen
import com.example.storemanagement.ui.feature.master.products.ProductScreen

fun NavGraphBuilder.categories(navHostController: NavHostController) {
    navigation(startDestination = Screens.CategoriesScreen.route, route = CATEGORY) {
        composable(route = Screens.ProductScreen.route) {
            ProductScreen(navController = navHostController)
        }
        composable(route = Screens.AddCategoryScreen.route) {
            AddCategoryScreen(navController = navHostController)
        }
    }
}
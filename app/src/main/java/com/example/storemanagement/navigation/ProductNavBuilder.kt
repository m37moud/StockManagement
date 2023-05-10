package com.example.storemanagement.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.storemanagement.ui.feature.master.categories.add_category.AddCategoryScreen
import com.example.storemanagement.ui.feature.master.products.ProductScreen
import com.example.storemanagement.ui.feature.master.products.add_product.AddProductScreen


@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.products(navHostController: NavHostController) {
    navigation(startDestination = Screens.ProductScreen.route, route = PRODUCT) {
        composable(route = Screens.ProductScreen.route) {
            ProductScreen(navController = navHostController)
        }
        composable(route = Screens.AddProductScreen.route) {
            AddProductScreen(navController = navHostController)
        }
    }
}
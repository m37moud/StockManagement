package com.example.storemanagement.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.storemanagement.ui.feature.master.categories.CategoriesScreen

fun NavGraphBuilder.master(navHostController: NavHostController) {
    navigation(startDestination = Screens.CategoriesScreen.route, route = MASTER) {

        composable(route = Screens.CategoriesScreen.route) {
            CategoriesScreen(navController = navHostController)
        }
        categories(navHostController = navHostController)

    }

}
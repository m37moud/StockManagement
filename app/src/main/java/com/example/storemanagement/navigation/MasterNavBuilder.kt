package com.example.storemanagement.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.storemanagement.ui.feature.master.MasterScreen
import com.example.storemanagement.ui.feature.master.categories.CategoriesScreen

fun NavGraphBuilder.master(navHostController: NavHostController) {
    navigation(startDestination = Screens.StockScreen.route, route = MASTER) {

//        composable(route = Screens.StockScreen.route) {
//            MasterScreen(navController = navHostController)
//        }
        products(navHostController = navHostController)
        categories(navHostController = navHostController)

    }

}
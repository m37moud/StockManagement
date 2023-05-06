package com.example.storemanagement.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.storemanagement.ui.feature.ChooserScreen
import com.example.storemanagement.ui.feature.scan.Scanner

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.ChooserScreen.route) {
        composable(route = Screens.ChooserScreen.route) {
            ChooserScreen(navController)
        }
        composable(route = Screens.ScannerScreen.route) {
            Scanner(navController = navController)
        }
    }

}
package com.example.storemanagement.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.storemanagement.ui.feature.ChooserScreen
import com.example.storemanagement.ui.feature.master.MasterScreen
import com.example.storemanagement.ui.feature.scan.Scanner
import com.example.storemanagement.ui.feature.settings.SettingsScreen
import com.example.storemanagement.ui.feature.share.ShareScreen
import com.example.storemanagement.ui.feature.splash.AnimatedSplashScreen

fun NavGraphBuilder.home(navHostController: NavHostController) {
    composable(route = Screens.SplashScreen.route) {
        AnimatedSplashScreen(navHostController)
    }
    composable(route = Screens.ChooserScreen.route) {
        ChooserScreen(navHostController)
    }
    composable(route = Screens.ScannerScreen.route) {
        Scanner(navController = navHostController)
    }
    composable(route = Screens.StockScreen.route) {
        MasterScreen(navController = navHostController)
    }
    composable(route = Screens.ShareScreen.route) {
        ShareScreen(navController = navHostController)
    }
    composable(route = Screens.SettingsScreen.route) {
        SettingsScreen(navController = navHostController)
    }
}
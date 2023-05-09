package com.example.storemanagement.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.storemanagement.ui.feature.ChooserScreen
import com.example.storemanagement.ui.feature.master.MasterScreen
import com.example.storemanagement.ui.feature.scan.Scanner

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.ChooserScreen.route,
        route = MAIN_ROOT
    ) {
        home(navController)
        master(navController)

    }

}
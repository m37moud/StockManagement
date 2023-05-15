package com.example.storemanagement.ui.feature.master

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.storemanagement.R
import com.example.storemanagement.navigation.Screens
import com.example.storemanagement.ui.component.ActionTopAppbar
import com.example.storemanagement.ui.component.ItemBox

@Composable
fun MasterScreen(navController: NavController) {
    Scaffold(

        modifier = Modifier
            .fillMaxWidth()
            .background(SnackbarDefaults.backgroundColor),
        topBar = {
            ActionTopAppbar(
                title = "Master Screen",
                onBack = { navController.popBackStack() },
//                elevation = 8.dp
            )
        },

        )
    {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ItemBox(
                modifier = Modifier.size(150.dp),
                name = "Categories",
                img = R.drawable.ic_category
            ) {
                navController.navigate(Screens.CategoriesScreen.route)
            }
            ItemBox(
                modifier = Modifier.size(150.dp),
                name = "Products",
                img = R.drawable.ic_product
            ) {
                navController.navigate(Screens.ProductScreen.route)
            }
        }
    }

}
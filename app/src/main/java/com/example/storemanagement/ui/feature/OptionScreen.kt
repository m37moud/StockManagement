package com.example.storemanagement.ui.feature

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.storemanagement.R
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.storemanagement.navigation.Screens
import com.example.storemanagement.ui.component.ItemBox
import com.example.storemanagement.ui.theme.StoreManagementTheme

@Composable
fun ChooserScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Store Management System",

//            style = MaterialTheme.typography.h5
            style = TextStyle(
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),

            Arrangement.SpaceEvenly
        ) {
            ItemBox(
                modifier = Modifier.size(150.dp),
                name = "Qr Scanner",
                img = R.drawable.ic_camera
            ) {
                navController.navigate(Screens.ScannerScreen.route)
                {
                    popUpTo(Screens.ChooserScreen.route) {
//                        inclusive = true
                        saveState = true

                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
            ItemBox(
                modifier = Modifier.size(150.dp),
                name = "Stock",
                img = R.drawable.ic_stock
            ){
                navController.navigate(Screens.StockScreen.route)

            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            Arrangement.SpaceEvenly
        ) {
            ItemBox(
                modifier = Modifier.size(150.dp),
                name = "Settings",
                img = R.drawable.ic_settings
            ){
                navController.navigate(Screens.SettingsScreen.route)

            }
            ItemBox(
                modifier = Modifier.size(150.dp),
                name = "Share",
                img = R.drawable.ic_share
            ){
                navController.navigate(Screens.ShareScreen.route)

            }

        }
        Spacer(modifier = Modifier.weight(1f))

    }

}


@Preview(showBackground = true)
@Composable
fun PreviewChooserScreen() {
    StoreManagementTheme {
        ChooserScreen(navController = rememberNavController())
    }

}

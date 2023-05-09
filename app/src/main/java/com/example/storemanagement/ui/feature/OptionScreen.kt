package com.example.storemanagement.ui.feature

import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.storemanagement.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.storemanagement.navigation.Screens
import com.example.storemanagement.ui.component.ItemBox

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
        Text(text = "Store Management System", style = MaterialTheme.typography.h5)
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

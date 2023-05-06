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
            )


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
            )
            ItemBox(
                modifier = Modifier.size(150.dp),
                name = "Share",
                img = R.drawable.ic_share
            )

        }
        Spacer(modifier = Modifier.weight(1f))

    }

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ItemBox(
    modifier: Modifier = Modifier,
    name: String,
    img: Int = R.drawable.ic_camera,
    onClick: () -> Unit = {}
) {
    val painter = rememberImagePainter(img)

    Card(modifier.clickable { onClick() }, elevation = 10.dp) {
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painter,
                contentDescription = "img",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .align(CenterHorizontally),
                contentScale = ContentScale.Inside,

//
            )
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(CenterHorizontally),
                elevation = 15.dp
            ) {

                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

        }

    }
}
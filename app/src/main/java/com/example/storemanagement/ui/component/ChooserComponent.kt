package com.example.storemanagement.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.storemanagement.R


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ItemBox(
    modifier: Modifier = Modifier,
    name: String,
    img: Int = R.drawable.ic_camera,
    onClick: () -> Unit = {}
) {
    val painter = rememberImagePainter(img)

    Card(
        modifier.clickable { onClick() },
        elevation = 10.dp,
//        border = BorderStroke(1.dp, color = Color(0xffFFA000))
    ) {
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painter,
                contentDescription = "img",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Inside,

//
            )
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterHorizontally),
                shape = CircleShape,
                elevation = 15.dp,
                backgroundColor = Color(0xffFFA000)
            ) {

                Text(
                    modifier = Modifier.padding(4.dp),
                    text = name,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

        }

    }
}
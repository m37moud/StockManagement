package com.example.storemanagement.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.storemanagement.R


@Composable
fun ErrorUI(error: String = "Something went wrong, try again in a few minutes. ¯\\_(ツ)_/¯") {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(70.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_sad),
//            imageVector = Icons.TwoTone.Favorite,
            modifier = Modifier.size(150.dp),
            contentDescription = "Logo",

            )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = error,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 72.dp, vertical = 72.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.error,
        )
//        Image(painter = painterResource("drawables/emptyerror.png"), contentDescription = null)
    }
}

@Composable
fun LoadingUI() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .defaultMinSize(minWidth = 96.dp, minHeight = 96.dp),
        )
    }
}


@Composable
fun LoadingScreen() {
//    Surface(
////        color = MaterialTheme.colors.primary,
//
//    ) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LinearProgressIndicator(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .width(128.dp)
                .padding(16.dp)
        )

        Text(
            text = "Loading",
//                style = mainTitle.copy(color = MaterialTheme.colors.secondary),
            modifier = Modifier.wrapContentSize()
        )
    }
//    }
}




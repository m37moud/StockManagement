package com.example.storemanagement.ui.feature.scanner
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//
//@Composable
//fun ScannerScreen(navController: NavController, scannerViewModel : ScannerViewModel = hiltViewModel()){
//
//    val state = scannerViewModel.state.collectAsState()
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(vertical = 10.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Box(modifier = Modifier
//            .fillMaxSize()
//            .weight(0.5f), contentAlignment = Alignment.Center) {
//            Text(text =  state.value.details )
//        }
//
//        Box(modifier = Modifier
//            .fillMaxSize()
//            .weight(0.5f), contentAlignment = Alignment.BottomCenter) {
//            Button(onClick = { scannerViewModel.startScanning() }) {
//                Text(text = "start scanning")
//            }
//        }
//
//
//    }
//
//}
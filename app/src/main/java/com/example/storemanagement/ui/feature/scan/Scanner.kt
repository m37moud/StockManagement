package com.example.storemanagement.ui.feature.scan

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.storemanagement.R
import com.example.storemanagement.model.ScannedItem
import com.example.storemanagement.ui.component.BarcodeScanner
import com.example.storemanagement.ui.component.Counter
import com.example.storemanagement.ui.component.MyTextDateField
import dagger.hilt.android.internal.Contexts.getApplication
import java.lang.Thread.sleep
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun Scanner(navController: NavController, scannerViewModel: ScannerViewModel = viewModel()) {
    var code by remember {
        mutableStateOf("scanning")
    }
    var sameItem = remember {
        mutableStateOf(false)
    }
    var scanFlag = remember {
        mutableStateOf(false)
    }
//    val flag = scannerViewModel.scanFlag.collectAsState().value
    val scanList = scannerViewModel.scannedItems.collectAsState().value
//    scanFlag.value = flag
    val context = LocalContext.current

    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )
    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        if (hasCamPermission) {

            val testList = remember {
                mutableListOf<ScannedItem>(
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            {


                BarcodeScanner(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                    scanFlag,
                    onBarcodeScanned = { barCode ->
                        scanFlag.value = true

                        scannerViewModel.onBarcodeScanned(barCode)
                        sleep(1000)

                        scanFlag.value = false
//                        if (checkBarCode(productList = testList, barCode = barCode)) {
//                            sameItem.value = true
//                            code = barCode
//
//                            sleep(1000)
//
//                            scanFlag.value = false
//
//
//                        } else {
//                            scanFlag.value = true
//
//                            code = barCode
//                            ScannedItem(barCode = barCode, count = 1).apply {
//                                Log.d("Scanner", " barCode")
//                                testList.add(this)
//                            }
//                            sleep(1000)
//                            scanFlag.value = false
//
//                        }
                    }, onError = { err ->
                        code = err
                    }
                )
            }


            if (scanList.isEmpty())
                EmptyScreen()
            else
                ProductLazyList(scanList, sameItem,
                    onDelete = { item ->
                        testList.remove(item)
                    })

        }
    }
}

@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Scan Bar Code to Add Items")
    }
}

//
//private fun checkBarCode(productList: List<ScannedItem>, barCode: String): Boolean {
//    val barCodeList = productList.map { item ->
//        item.barCode
//    }
//    if (barCodeList.contains(barCode))
//        return true
//    return false
//}

@Composable
fun ProductLazyList(
    list: List<ScannedItem>,
    sameItem: MutableState<Boolean>,
    onDelete: (product: ScannedItem) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        content = {
            items(list) { product ->
                ProductItem(
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize(),
                    scannedItem = product, sameItem = sameItem, onDelete = { onDelete(product) }
                )
            }
        })

}

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    scannedItem: ScannedItem,
    sameItem: MutableState<Boolean>,
    onDelete: () -> Unit
) {
    var counter = remember { mutableStateOf(1) }
    val date = LocalDate.now(ZoneId.systemDefault())
    val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val formateDate = date.format(pattern)
    var expDate = remember { mutableStateOf(TextFieldValue(formateDate)) }

//    if (sameItem.value) {
//        counter.value.plus(1)
//    }

    Column(modifier = Modifier) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = scannedItem.barCode, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Counter(modifier = Modifier, counter = counter, onMinus = {
                        counter.value--
                    }, onAdd = {
                        counter.value++
                    })
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = scannedItem.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(text = scannedItem.unit, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(text = "EXp : ", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    MyTextDateField(expDate)
                }
            }


            IconButton(onClick = { onDelete() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "delete"
                )
            }
        }

        Divider(
            startIndent = 0.dp,
            thickness = 2.dp,
            color = MaterialTheme.colors.onSecondary,
        )
    }
}






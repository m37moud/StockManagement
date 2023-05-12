package com.example.storemanagement.ui.feature.scan

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
//    var sameItem = remember {
//        mutableStateOf(false)
//    }
    var scanFlag = remember {
        mutableStateOf(false)
    }
    val sameItem = scannerViewModel.sameItem.collectAsState().value
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
                    }, onError = { err ->
                        code = err
                    }
                )
            }


            if (scanList.isEmpty())
                EmptyScreen()
            else
                ProductLazyList(scanList,
                    onMinus = { item, count ->
                        scannerViewModel.updateCount(item, count)

                    }, onAdd = { item, count ->
                        scannerViewModel.updateCount(item, count)


                    },
                    onUpdate = { item ->
                        scannerViewModel.updateExpDate(item)

                    },
                    onDelete = { item ->
                        scannerViewModel.removeItem(item)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductLazyList(
    list: List<ScannedItem>,
    onMinus: (ScannedItem, Int) -> Unit,
    onAdd: (ScannedItem, Int) -> Unit,
    onUpdate: (ScannedItem) -> Unit,
    onDelete: (product: String) -> Unit
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray.copy(alpha = .4f)),
        contentPadding = PaddingValues(4.dp),
        content = {
            items(items = list, key = { it.barCode }) { product ->
                ProductItem(
                    modifier = Modifier
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 1000,
                            )
                        )
                        .fillMaxSize()
//                        .animateContentSize()
                    ,
                    scannedItem = product,
                    onMinus = {
                        onMinus(product, -1)
                    }, onAdd = {
                        onAdd(product, 1)

                    },
                    onUpdate = { onUpdate(it) },
                    onDelete = { onDelete(product.barCode) }
                )
            }
        })

}

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    scannedItem: ScannedItem,
    onUpdate: (ScannedItem) -> Unit,
    onMinus: () -> Unit,
    onAdd: () -> Unit,

    onDelete: (String) -> Unit
) {

    var counter = remember { mutableStateOf(0) }
//    var item = remember {
//        ScannedItem(
//
//        )
//    }
    counter.value = scannedItem.count
    val date = LocalDate.now(ZoneId.systemDefault())
    val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val formateDate = date.format(pattern)
    var expDate = remember { mutableStateOf(TextFieldValue(formateDate)) }


    Card(
        modifier = modifier, elevation = 4.dp

    ) {

        Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = scannedItem.barCode,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Counter(modifier = Modifier, counter = counter, onMinus = {
                            onMinus()
                            Log.d("ScannerScreen", "count${counter.value} ")


                        }, onAdd = {
                            onAdd()
                            Log.d("ScannerScreen", "count${counter.value} ")

                        }, onDone = {
                            onUpdate(
                                scannedItem.copy(
                                    count = counter.value,
                                )
                            )
                        })
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = scannedItem.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = scannedItem.unit,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(text = "EXp : ", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        MyTextDateField(expDate)
                    }
                }


                IconButton(onClick = { onDelete(scannedItem.barCode) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "delete"
                    )
                }
            }
//            item = scannedItem.copy(
////                barCode = scannedItem.barCode,
////                name = scannedItem.name,
////                unit = scannedItem.unit,
////                count = counter.value,
//                expDate = expDate.value.text
//            )
            LaunchedEffect(key1 = expDate.value.text) {
//                Log.d("ScannerScreen", "item${item.toString()} ")

                onUpdate(
                    scannedItem.copy(
//                barCode = scannedItem.barCode,
//                name = scannedItem.name,
//                unit = scannedItem.unit,
//                count = counter.value,
                        expDate = expDate.value.text
                    )
                )
            }

            Divider(
                startIndent = 0.dp,
                thickness = 2.dp,
                color = MaterialTheme.colors.onSecondary,
            )
        }
    }
}






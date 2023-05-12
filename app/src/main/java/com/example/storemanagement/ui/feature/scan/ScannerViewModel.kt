package com.example.storemanagement.ui.feature.scan

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.storemanagement.model.ScannedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor() : ViewModel() {
    private val _scannedItems = MutableStateFlow<List<ScannedItem>>(emptyList())
    val scannedItems: StateFlow<List<ScannedItem>> = _scannedItems.asStateFlow()

    //    private val _scannedItem = MutableStateListOf<ScannedItem>(emptyList())
//    val scannedItem: List<ScannedItem> = _scannedItem
//when observe 
    //val list = viewmodel.scannedItems
    private var scanList: MutableSet<ScannedItem> = mutableSetOf()

    private val _sameItem = MutableStateFlow<Boolean>(false)
    val sameItem: StateFlow<Boolean> = _sameItem.asStateFlow()

    private val _scanFlag = MutableStateFlow<Boolean>(false)
    val scanFlag: StateFlow<Boolean> = _scanFlag

    fun onBarcodeScanned(barCode: String) {
        val items = _scannedItems.value.toMutableList()
        if (checkBarCode(productList = items, barCode = barCode)) {
            Log.d("ScannerViewModel", "onBarcodeScanned bar code found")
            _sameItem.value = true
            items.find { it.barCode == barCode }?.let {
                val index = items.indexOf(it)
                val updatedItem = it.copy(count = it.count + 1)
                val updatedItems = items.apply { set(index, updatedItem) }

                _scannedItems.value = updatedItems
                Log.d(
                    "ScannerViewModel",
                    "onBarcodeScanned bar code found ${_scannedItems.value.toString()}"
                )

            }
        } else {
            Log.d("ScannerViewModel", "onBarcodeScanned bar code not found")

            _sameItem.value = false
            val date = LocalDate.now(ZoneId.systemDefault())
            val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val formateDate = date.format(pattern)
            val newItem = ScannedItem(barCode = barCode, count = 1, expDate = formateDate)
            _scannedItems.value = items + newItem
        }
    }

    private fun checkBarCode(productList: List<ScannedItem>, barCode: String): Boolean {

        val tempList = productList.map {
            it.barCode
        }
        if (tempList.contains(barCode))
            return true
        return false
    }

    fun removeItem(barCode: String) {
        Log.d("ScannerViewModel", "removeItem barCode = $barCode")

        val items = _scannedItems.value.toMutableList()
        items.find { it.barCode == barCode }?.let {
            val index = items.indexOf(it)
            Log.d("ScannerViewModel", "removeItem index = $index")

            items.removeAt(index)
        }
        Log.d("ScannerViewModel", "removeItem barCode = ${items.toString()}")

        _scannedItems.value = items
//        _scannedItems.value = items - item
    }

    fun updateExpDate(item: ScannedItem) {
        Log.d("ScannerViewModel", "item  = ${item.toString()}")

        val items = _scannedItems.value.toMutableList()
        items.find { it.barCode == item.barCode }?.let {
            val index = items.indexOf(it)
            val updatedItem = it.copy(
                barCode = item.barCode,
                name = item.name,
                unit = item.unit,
                count = item.count,
                expDate = item.expDate
            )
            val updatedItems = items.apply { set(index, updatedItem) }
            Log.d("ScannerViewModel", "updateExpDate  = ${updatedItems.toString()}")

            _scannedItems.value = updatedItems
        }
    }
    fun updateCount(item: ScannedItem ,count:Int) {
        val items = _scannedItems.value.toMutableList()
        items.find { it.barCode == item.barCode }?.let {
            val index = items.indexOf(it)
            val updatedItem = it.copy(
                count = item.count.plus(count),
            )
            val updatedItems = items.apply { set(index, updatedItem) }
            Log.d("ScannerViewModel", "updateExpDate  = ${updatedItems.toString()}")

            _scannedItems.value = updatedItems
        }
    }
}

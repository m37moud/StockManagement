package com.example.storemanagement.ui.feature.scan

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.storemanagement.model.ScannedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(): ViewModel() {
    private val _scannedItems = MutableStateFlow<List<ScannedItem>>(emptyList())
    val scannedItems: StateFlow<List<ScannedItem>> = _scannedItems
    
  //  private val _scannedItems = MutableStateListOf<ScannedItem>(emptyList())
   // val scannedItems: List<ScannedItem> = _scannedItems
//when observe 
    //val list = viewmodel.scannedItems
    private var scanList: MutableSet<ScannedItem> = mutableSetOf()

    private val _sameItem = MutableStateFlow<Boolean>(false)
    val sameItem: StateFlow<Boolean> = _sameItem
    private val _scanFlag = MutableStateFlow<Boolean>(false)
    val scanFlag: StateFlow<Boolean> = _scanFlag

    fun onBarcodeScanned(barCode: String) {
        val items = _scannedItems.value
        if (checkBarCode(productList = items, barCode = barCode)) {
            Log.d("ScannerViewModel","onBarcodeScanned bar code found")
            _sameItem.value = true
            items.find { it.barCode == barCode }?.let {
                val index = items.indexOf(it)
                val updatedItem = it.copy(count = it.count + 1)
                val updatedItems = items.toMutableList().apply { set(index, updatedItem) }
                Log.d("ScannerViewModel","onBarcodeScanned bar code found ${updatedItems.toString()}")

                _scannedItems.value = updatedItems

            }
        } else {
            Log.d("ScannerViewModel","onBarcodeScanned bar code not found")

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

    fun removeItem(item: ScannedItem) {
        val items = _scannedItems.value.orEmpty()
        items.find{it.barCode == item.barCode}?.let {
            val index = items.indexOf(it)
            items.toMutableList().removeAt(index)
        }
        _scannedItems.value = items
//        _scannedItems.value = items - item
    }

    fun updateExpDate(item: ScannedItem, expDate: String) {
        val items = _scannedItems.value.orEmpty()
        items.find { it.barCode == item.barCode }?.let {
            val index = items.indexOf(it)
            val updatedItem = it.copy(expDate = expDate)
            val updatedItems = items.toMutableList().apply { set(index, updatedItem) }
            _scannedItems.value = updatedItems
        }
    }
}

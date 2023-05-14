package com.example.storemanagement.ui.feature.master.categories.add_category

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storemanagement.MainActivity
import com.example.storemanagement.data.database.entity.CategoryEntity
import com.example.storemanagement.excel.MainScreenState
import com.example.storemanagement.repository.Repository
import com.example.storemanagement.util.ExcelUtil
import com.example.storemanagement.util.UriPathFinder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val repository: Repository,
    private val uriPathFinder: UriPathFinder,
    private val excelUtil: ExcelUtil,
) : ViewModel() {
    val TAG = MainActivity::class.java.simpleName
    private var _message = MutableStateFlow<String>("")
    val message: StateFlow<String> = _message


    var state by mutableStateOf(MainScreenState())
        private set


    fun insertCategory(categoryEntity: CategoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (categoryEntity.categoryName.isNotBlank()) {

                    val isCategory =
                        repository.local.getCategoryByName(categoryName = categoryEntity.categoryName)

                    if (isCategory == null) {
                        repository.local.insertCategory(categoryEntity)
                        _message.value = "Insert Successful"
                    } else {
                        _message.value = "this category already added"

                    }
                } else {
                    _message.value = "please enter category name"

                }

            } catch (exc: Exception) {
                _message.value = "error ${exc.stackTraceToString()}"

            }
            _message.value = ""

        }
    }

//    fun onFilePathsListChange(list:Uri, context: Context){
//        val updatedList = state.filePaths.toMutableList()
//        val paths = changeUriToPath(list,context)
//        viewModelScope.launch {
//            updatedList += paths
//            state = state.copy(
//                filePaths = updatedList
//            )
//        }
//    }

    fun onFilePathsListChange(uri: Uri, context: Context) {
        val updatedList = state.filePath.toMutableList()
        val paths = changeUriToPath(uri, context)
        viewModelScope.launch {
//            updatedList += paths
            state = paths?.let {
                state.copy(
                    uri = uri,
                    filePath = it
                )
            }!!
        }
    }

    fun readExcelNew(context: Context, uri: Uri?, filePath: String?) {

        val result = excelUtil.readExcelNew(context, uri, filePath)
        Log.d(TAG, "readExcelNew ${result.toString()}")

    }

    private fun changeUriToPath(uri: Uri, context: Context): String? {
        return uriPathFinder.getPath(context, uri)
    }


}
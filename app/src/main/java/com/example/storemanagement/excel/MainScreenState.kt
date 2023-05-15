package com.example.storemanagement.excel

import android.net.Uri

data class MainScreenState(
//    val filePaths:List<String> = emptyList()
    val uri: Uri? = null,
    val filePath: String = "",
    val startImporter : Boolean = false,
    val message :String = ""
)
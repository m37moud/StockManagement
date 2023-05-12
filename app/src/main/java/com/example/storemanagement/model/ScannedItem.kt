package com.example.storemanagement.model

data class ScannedItem(
    val barCode: String = "",
    val name: String = "Product name",
    val unit: String = "pcs",
    val count: Int = 1,
    val expDate: String = ""
)

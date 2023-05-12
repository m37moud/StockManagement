package com.example.storemanagement.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.storemanagement.util.Constants.PRODUCT_TABLE

@Entity(tableName = PRODUCT_TABLE)

class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "barcode")
    var barcode: String,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "productName")
    var productName: String,
    @ColumnInfo(name = "unit")
    var unit: String,
) {
}
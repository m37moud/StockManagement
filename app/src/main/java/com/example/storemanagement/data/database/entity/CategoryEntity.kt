package com.example.storemanagement.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.storemanagement.util.Constants.CATEGORY_TABLE

@Entity(tableName = CATEGORY_TABLE)
class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "category_name")
    var categoryName: String
)
package com.example.storemanagement.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.storemanagement.data.database.dao.CategoriesDao
import com.example.storemanagement.data.database.dao.ProductsDao
import com.example.storemanagement.data.database.entity.CategoryEntity
import com.example.storemanagement.data.database.entity.ProductEntity

@Database(
    entities = [CategoryEntity::class, ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoriesDao
    abstract fun productsDao(): ProductsDao
}
package com.example.storemanagement.data.database.dao

import androidx.room.*
import com.example.storemanagement.data.database.entity.ProductEntity

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Update(entity = ProductEntity::class)
    suspend fun updateProduct(productEntity: ProductEntity)

    @Query("DELETE FROM product_table WHERE id = :tid")
    suspend fun deleteProduct(tid : String)

    @Query("DELETE FROM product_table")
    suspend fun deleteAllProducts()
}
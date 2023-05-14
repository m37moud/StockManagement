package com.example.storemanagement.data.database.dao

import androidx.room.*
import com.example.storemanagement.data.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category_table WHERE category_name = :categoryName")
    fun getCategoryByName(categoryName : String) : CategoryEntity?

    @Query("SELECT * FROM category_table ORDER BY id ASC")
    fun readAllCategories(): Flow<List<CategoryEntity>>

    @Update(entity = CategoryEntity::class)
    suspend fun updateCategory(categoryEntity: CategoryEntity)

    @Query("DELETE FROM category_table WHERE id = :tid")
    suspend fun deleteCategory(tid : Int)

    @Query("DELETE FROM category_table")
    suspend fun deleteAllCategories()
}
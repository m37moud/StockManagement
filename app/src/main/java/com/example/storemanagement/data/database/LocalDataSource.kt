package com.example.storemanagement.data.database

import com.example.storemanagement.data.database.dao.CategoriesDao
import com.example.storemanagement.data.database.dao.ProductsDao
import com.example.storemanagement.data.database.entity.CategoryEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val categoriesDao: CategoriesDao,
    private val productsDao: ProductsDao,
) {
    //category
    fun readAllCategories() = categoriesDao.readAllCategories()
    fun getCategoryByName(categoryName : String) = categoriesDao.getCategoryByName(categoryName)
    suspend fun insertCategory(categoryEntity: CategoryEntity) = categoriesDao.insertCategory(categoryEntity)
    suspend fun updateCategory(categoryEntity: CategoryEntity) = categoriesDao.updateCategory(categoryEntity)
    suspend fun deleteCategory(tid : Int) = categoriesDao.deleteCategory(tid)
    suspend fun deleteAllCategories() = categoriesDao.deleteAllCategories()


}
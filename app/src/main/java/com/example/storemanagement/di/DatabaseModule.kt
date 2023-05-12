package com.example.storemanagement.di

import android.content.Context
import androidx.room.Room
import com.example.storemanagement.data.database.InventoryDatabase
import com.example.storemanagement.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        InventoryDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideCategoryDao(inventoryDatabase: InventoryDatabase) = inventoryDatabase.categoryDao()

    @Provides
    @Singleton
    fun provideProductDao(inventoryDatabase: InventoryDatabase) = inventoryDatabase.productsDao()
}
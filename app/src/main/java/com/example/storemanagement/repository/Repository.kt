package com.example.storemanagement.repository

import com.example.storemanagement.data.database.LocalDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    val local = localDataSource
}
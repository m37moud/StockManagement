package com.example.storemanagement.repository

import kotlinx.coroutines.flow.Flow

interface ScannerRepo {
    fun startScanning(): Flow<String?>
}
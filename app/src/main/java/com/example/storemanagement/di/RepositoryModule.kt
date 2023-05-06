package com.example.storemanagement.di
//
//import com.example.storemanagement.repository.ScannerRepo
//import com.example.storemanagement.repository.ScannerRepoImpl
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ViewModelComponent
//import dagger.hilt.android.scopes.ViewModelScoped
//
//
//@Module
//@InstallIn(ViewModelComponent::class)
//abstract class RepositoryModule {
//
//    @Binds
//    @ViewModelScoped
//    abstract fun bindMainRepo(
//        scannerRepoImpl : ScannerRepoImpl
//    ) : ScannerRepo
//
//}
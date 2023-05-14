package com.example.storemanagement.di
//
import com.example.storemanagement.util.ExcelUtil
import com.example.storemanagement.util.UriPathFinder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

//import dagger.hilt.android.scopes.ViewModelScoped
//
@InstallIn(ViewModelComponent::class)
@Module
object AppModule {

    @ViewModelScoped
    @Provides
    fun provideUriPathFinder() = UriPathFinder()

    @ViewModelScoped
    @Provides
    fun provideExcelUtil() = ExcelUtil()

////    @ViewModelScoped
////    @Provides
////    fun provideContext(app: Application): Context {
////        return app.applicationContext
////    }
////
////    @ViewModelScoped
////    @Provides
////    fun provideBarCodeOptions() : GmsBarcodeScannerOptions {
////        return GmsBarcodeScannerOptions.Builder()
////            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
////            .build()
////    }
////
////    @ViewModelScoped
////    @Provides
////    fun provideBarCodeScanner(context: Context,options: GmsBarcodeScannerOptions): GmsBarcodeScanner {
////        return GmsBarcodeScanning.getClient(context, options)
////    }
}
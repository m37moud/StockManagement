package com.example.storemanagement.navigation

const val MASTER = "master"
const val HOME = "home"
const val CATEGORY = "category"
const val PRODUCT = "product"
const val MAIN_ROOT = "root"
sealed class Screens(val route : String ){
    object ChooserScreen : Screens(route = "chooser")
    object ScannerScreen : Screens(route = "Scanner")
    object StockScreen : Screens(route = "stock")
    object SettingsScreen : Screens(route = "setting")
    object ShareScreen : Screens(route = "share")

    object CategoriesScreen : Screens(route = "categories")
    object AddCategoryScreen : Screens(route = "add_category")

    object ProductScreen : Screens(route = "products")
    object AddProductScreen : Screens(route = "add_product")


}

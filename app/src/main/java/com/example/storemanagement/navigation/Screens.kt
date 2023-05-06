package com.example.storemanagement.navigation

sealed class Screens(val route : String ){
    object ChooserScreen : Screens(route = "chooser")
    object ScannerScreen : Screens(route = "Scanner")
    object StockScreen : Screens(route = "stock")
    object SettingsScreen : Screens(route = "setting")
    object ShareScreen : Screens(route = "share")
}

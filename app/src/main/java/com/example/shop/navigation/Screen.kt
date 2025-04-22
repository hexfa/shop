package com.example.shop.navigation

sealed class Screen(val rout :String) {
    object splash: Screen("Splash_Screen")
}
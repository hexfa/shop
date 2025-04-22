package com.example.shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SetUpNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        startDestination =Screen.splash.rout
    ){

    }
}
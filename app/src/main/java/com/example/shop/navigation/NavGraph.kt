package com.example.shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shop.ui.screens.SplashScreen

@Composable
fun SetUpNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        startDestination =Screen.splash.rout
    ){
        composable(route =Screen.splash.rout ){
            SplashScreen(navController=navController)
        }
    }
}
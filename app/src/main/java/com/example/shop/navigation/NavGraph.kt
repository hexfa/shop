package com.example.shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shop.ui.screens.BasketScreen
import com.example.shop.ui.screens.HomeScreen
import com.example.shop.ui.screens.ProfileScreen
import com.example.shop.ui.screens.SplashScreen

@Composable
fun SetUpNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        startDestination =Screen.Splash.route
    ){
        composable(route =Screen.Splash.route ){
            SplashScreen(navController=navController)
        }

        composable(route=Screen.Home.route){
            HomeScreen(navController=navController)
        }

        composable(route=Screen.Basket.route){

            BasketScreen(navController=navController)
        }

        composable(route=Screen.Profile.route){
            ProfileScreen(navController=navController)
        }
    }
}
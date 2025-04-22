package com.example.shop.ui.screens

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.shop.ui.theme.splashBg


@Composable
fun SplashScreen(navController: NavHostController){
    Text("SplashScreen")

}

@Composable
fun Splash(){
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.splashBg).fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

    }
}
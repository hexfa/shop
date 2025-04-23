package com.example.shop.ui.screens

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shop.R
import com.example.shop.navigation.Screen
import com.example.shop.ui.theme.splashBg
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController){

    Splash()
    LaunchedEffect(true) {
        delay(2000)
        navController.navigate(Screen.Home.route)
    }
}

@Composable
fun Splash(){
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.splashBg).fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Image(painter = painterResource(R.drawable.digi_logo)
            ,contentDescription = null, modifier = Modifier.size(250.dp))
    }

    Box(modifier = Modifier.fillMaxSize()
        .padding(100.dp)
        ,
        contentAlignment = Alignment.BottomCenter


    ){
        Image(painter = painterResource(R.drawable.digi_txt_white),
            contentDescription = null,
            modifier = Modifier.height(30.dp),
            )

    }
}
package com.example.shop.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shop.viewmodels.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController){

    Home(navController)
}

@Composable
fun Home(navController: NavHostController,
         viewModel: HomeViewModel = hiltViewModel()
){
    Column (modifier =
    Modifier
        .background(color = Color.White)
        .fillMaxSize()
    ){

      val refreshScope= rememberCoroutineScope()

    }

}
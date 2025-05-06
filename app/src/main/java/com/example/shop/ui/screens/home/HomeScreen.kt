package com.example.shop.ui.screens.home

import androidx.compose.runtime.Composable
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

}
package com.example.shop.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shop.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TopSlider(viewModel: HomeViewModel= hiltViewModel()){
    LaunchedEffect(true) {
        viewModel.slider.collectLatest {  }
    }

}
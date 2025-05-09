package com.example.shop.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shop.data.remote.NetworkResult
import com.example.shop.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TopSlider(viewModel: HomeViewModel= hiltViewModel()){
    LaunchedEffect(true) {
        viewModel.slider.collectLatest {result->
            when (result){
                is NetworkResult.Success->{}

                is NetworkResult.Error->{}

                is NetworkResult.Loading->{}


            }
        }
    }

}
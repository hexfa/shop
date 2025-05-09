package com.example.shop.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shop.data.model.home.Slider
import com.example.shop.data.remote.NetworkResult
import com.example.shop.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TopSlider(viewModel: HomeViewModel= hiltViewModel()){

    var list by remember {
        mutableStateOf<List<Slider>>(emptyList())

    }


    var loading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.slider.collectLatest {result->
            when (result){
                is NetworkResult.Success->{
                    result.data?.let {
                        list=it
                    }
                }

                is NetworkResult.Error->{}

                is NetworkResult.Loading->{}


            }
        }
    }

}
package com.example.shop.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shop.data.model.home.Slider
import com.example.shop.data.remote.NetworkResult
import com.example.shop.viewmodels.HomeViewModel

@Composable
fun TopSlider(viewModel: HomeViewModel= hiltViewModel()) {

    var list by remember {
        mutableStateOf<List<Slider>>(emptyList())

    }


    var loading by remember {
        mutableStateOf(false)
    }

    val sliderResult by viewModel.slider.collectAsState()

    when (sliderResult) {
        is NetworkResult.Success -> {
            list=sliderResult.data?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false

        }

        is NetworkResult.Loading -> {
            loading = true
        }


    }

    Column (modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(Color.White)
    ){
        if (loading){

        }else{

        }
    }
}



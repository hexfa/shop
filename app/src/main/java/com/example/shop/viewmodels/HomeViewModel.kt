package com.example.shop.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.data.model.home.Slider
import com.example.shop.data.remote.NetworkResult
import com.example.shop.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepo: HomeRepository):ViewModel(){
    val slider= MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
     fun getSlider(){
         viewModelScope.launch {

             slider.value = NetworkResult.Loading()

             slider.emit(homeRepo.getSlider())
             slider.emit(homeRepo.getSlider())


         }

    }

}
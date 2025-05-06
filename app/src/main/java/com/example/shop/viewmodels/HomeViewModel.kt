package com.example.shop.viewmodels

import androidx.lifecycle.ViewModel
import com.example.shop.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository):ViewModel(){
}
package com.example.shop.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.data.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val repository: DataStoreRepository
) :ViewModel(){

    companion object{
       const val USER_LANGUAGE_KEY="USER_LANGUAGE_KEY"
    }
    fun saveUserLanguage(value:String){
        viewModelScope.launch {
            repository.putString(USER_LANGUAGE_KEY,value)
        }
    }
}
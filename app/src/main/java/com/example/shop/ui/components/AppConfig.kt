package com.example.shop.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shop.utils.Constants
import com.example.shop.viewmodels.DataStoreViewModel

@Composable
fun AppConfig(
    dataStore: DataStoreViewModel = hiltViewModel()
){

    LaunchedEffect(true) {
        getDataStoreVariables(dataStore)
    }
}

private suspend fun getDataStoreVariables(dataStore: DataStoreViewModel){
    Constants.USER_LANGUAGE=dataStore.getUserLanguage()
}
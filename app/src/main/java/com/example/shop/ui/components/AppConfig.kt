package com.example.shop.ui.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shop.utils.Constants
import com.example.shop.viewmodels.DataStoreViewModel

@Composable
fun AppConfig(

    dataStore: DataStoreViewModel = hiltViewModel()
) {


    getDataStoreVariables(dataStore)
}


private fun getDataStoreVariables(dataStore: DataStoreViewModel) {

    Constants.USER_LANGUAGE = dataStore.getUserLanguage()

}


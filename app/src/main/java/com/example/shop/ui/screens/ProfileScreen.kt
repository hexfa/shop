package com.example.shop.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shop.MainActivity
import com.example.shop.utils.Constants.ENGLISH
import com.example.shop.utils.Constants.PERSIAN
import com.example.shop.viewmodels.DataStoreViewModel

@SuppressLint("ContextCastToActivity")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier

            .fillMaxSize()
            .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {



        val activity = LocalContext.current as Activity

        Text("profile")

        Button(onClick = {

            dataStore.saveUserLanguage(PERSIAN)
            activity.apply {
                finish()
                startActivity(Intent(activity, MainActivity::class.java))
            }


        }) {
            Text("fa")

        }



        Button(onClick = {
            dataStore.saveUserLanguage(ENGLISH)

            activity.apply {

                finish()

                startActivity(Intent(activity, MainActivity::class.java))
            }
        }) {
            Text("en")
        }
    }
}
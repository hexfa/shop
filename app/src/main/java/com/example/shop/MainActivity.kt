package com.example.shop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shop.navigation.BottomNavigationBar
import com.example.shop.navigation.SetUpNavGraph
import com.example.shop.ui.theme.ShopTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopTheme {

                navController = rememberNavController()
                Scaffold(bottomBar = {
                    BottomNavigationBar(navController = navController, onItemClick = {
                        navController.navigate(it.route)
                    })
                }) {
                    SetUpNavGraph(navController = navController)
                }
            }
        }
    }
}


package com.example.shop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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

                navController= rememberNavController()
                Scaffold (bottomBar = {
                    //todo bottombar
                }){
                    SetUpNavGraph(navController=navController)
                }
            }
        }
    }
}


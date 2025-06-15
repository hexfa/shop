package com.example.shop

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shop.navigation.BottomNavigationBar
import com.example.shop.navigation.SetUpNavGraph
import com.example.shop.ui.components.ChangeStatusBarColor
import com.example.shop.ui.theme.ShopTheme
import com.example.shop.utils.Constants.ENGLISH
import com.example.shop.utils.Constants.USER_LANGUAGE
import com.example.shop.utils.LocalUtils
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint




@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Composable


    private fun setupLocale() {


        LocalUtils.setLocale(LocalContext.current, USER_LANGUAGE)

    }



    private fun getLayoutDirection(): LayoutDirection {

        return if (USER_LANGUAGE == ENGLISH) {
            LayoutDirection.Ltr
        } else {


            LayoutDirection.Rtl

        }
    }

    private fun logAppConfig() {

        Log.d("MainActivity", "com.example.shop.data.model.User language: $USER_LANGUAGE, LayoutDirection: ${getLayoutDirection()}")
    }



    @Composable


    fun MainBottomBar(navController: androidx.navigation.NavHostController) {



        BottomNavigationBar(navController = navController, onItemClick = {

            navController.navigate(it.route)
        })

    }



    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")


    @Composable

    fun MainContent() {



        val navController = rememberNavController()


        Scaffold(bottomBar = {




            MainBottomBar(navController)
            
        }) {
            SetUpNavGraph(navController = navController)
        }
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        logAppConfig()
        setContent {
            ShopTheme {
                ConfigureApp(navController = rememberNavController())
                CompositionLocalProvider(LocalLayoutDirection provides getLayoutDirection()) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun ConfigureApp(navController: NavHostController? = null) {
    navController?.let {
        ChangeStatusBarColor(it)
    }
    // Add more app-wide configurations here
}
@Composable
fun ChangeStatusBarColor(navController: NavHostController) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = useDarkIcons
        )
    }
}



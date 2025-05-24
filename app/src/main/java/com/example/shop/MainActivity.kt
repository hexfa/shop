package com.example.shop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shop.navigation.BottomNavigationBar
import com.example.shop.navigation.SetUpNavGraph
import com.example.shop.ui.components.AppConfig
import com.example.shop.ui.components.ChangeStatusBarColor
import com.example.shop.ui.theme.ShopTheme
import com.example.shop.utils.Constants.ENGLISH
import com.example.shop.utils.Constants.USER_LANGUAGE
import com.example.shop.utils.LocalUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

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

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopTheme {

                navController = rememberNavController()

                ChangeStatusBarColor(navController)
                AppConfig()

                setupLocale()

                CompositionLocalProvider(LocalLayoutDirection provides getLayoutDirection()) {
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
}

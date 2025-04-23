package com.example.shop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.shop.R

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier=Modifier,
    onItemClick:(BottomNavItem)->Unit
){

    val items= listOf(
        BottomNavItem(
            name = "Home",
            route = Screen.Home.route,
            selectedIcon = painterResource(R.drawable.digi_logo),
            deSelectedIcon = painterResource(R.drawable.digi_logo)
        ),


    )
}
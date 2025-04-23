package com.example.shop.navigation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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

        BottomNavItem(
            name = "Category",
            route = Screen.Category.route,
            selectedIcon = painterResource(R.drawable.digi_logo),
            deSelectedIcon = painterResource(R.drawable.digi_logo)
        ),
        BottomNavItem(
            name = "Profile",
            route = Screen.Profile.route,
            selectedIcon = painterResource(R.drawable.digi_logo),
            deSelectedIcon = painterResource(R.drawable.digi_logo)
        ),

        BottomNavItem(
            name = "Basket",
            route = Screen.Basket.route,
            selectedIcon = painterResource(R.drawable.digi_logo),
            deSelectedIcon = painterResource(R.drawable.digi_logo)
        ),


    )
    val backStackEntry=navController.currentBackStackEntryAsState()
    val  showBottomBar=backStackEntry.value?.destination?.route in items.map { it.route }

    /*if(showBottomBar){
        BottomNavigation(
            modifier=Modifier,
            backGroundColor=Color.White,
            elevation=5.dp
        ){

            items.forEachIndexed{index,item->

                val selected=item.route==backStackEntry.value?.destination?.route
                BottomNavigationItem(selected=selected,onClick=onItemClick(item)}){
                    //unSelectedCenterColor=
            }
        }
    }*/


}
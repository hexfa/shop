package com.example.shop.navigation


//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shop.R

//noinspection UsingMaterialAndMaterial3Libraries

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

    if(showBottomBar){

        BottomNavigation(
            modifier=Modifier,
            backgroundColor =Color.White,
            elevation=5.dp
        ){

            items.forEachIndexed{index,item->

                val selected=item.route==backStackEntry.value?.destination?.route

                BottomNavigationItem(selected=selected,onClick=onItemClick(item)}){
                    //unSelectedCenterColor=
            }
        }
    }


}
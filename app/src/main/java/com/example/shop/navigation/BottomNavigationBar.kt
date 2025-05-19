package com.example.shop.navigation


//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shop.R
import com.example.shop.ui.theme.selectedBottomBar
import com.example.shop.ui.theme.unSelectedBottomBar

//noinspection UsingMaterialAndMaterial3Libraries

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val background = Color.White

    val navItems = listOf(
        BottomNavItem(
            name = stringResource(id = R.string.basket),
            route = Screen.Basket.route,
            selectedIcon = painterResource(R.drawable.cart_fill),
            deSelectedIcon = painterResource(R.drawable.cart_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.home),
            route = Screen.Home.route,
            selectedIcon = painterResource(R.drawable.home_fill),
            deSelectedIcon = painterResource(R.drawable.home_outline)
        ),

        BottomNavItem(
            name = stringResource(id = R.string.category),
            route = Screen.Category.route,
            selectedIcon = painterResource(R.drawable.cart_fill),
            deSelectedIcon = painterResource(R.drawable.category_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.profile),
            route = Screen.Profile.route,
            selectedIcon = painterResource(R.drawable.user_fill),
            deSelectedIcon = painterResource(R.drawable.user_outline)
        ),


        )
    val backStackEntry
    = navController.currentBackStackEntryAsState()
    val showBottomBar = navItems.any { it.route == backStackEntry.value?.destination?.route }
    val currentRoute = backStackEntry.value?.destination?.route
    if (showBottomBar) {

        BottomNavigation(
            modifier = Modifier,
            backgroundColor = background,
            elevation = 5.dp
        ) {

            navItems.forEach { navItem ->


                val selected = navItem .route == currentRoute

                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(navItem ) },
                    selectedContentColor = MaterialTheme.colors.selectedBottomBar,
                    unselectedContentColor = MaterialTheme.colors.unSelectedBottomBar,
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (selected) {
                                Icon(
                                    painter = navItem .selectedIcon,
                                    contentDescription = navItem .name,
                                    modifier = Modifier.height(24.dp)
                                )
                            } else {
                                Icon(
                                    painter = navItem .deSelectedIcon,
                                    contentDescription = navItem .name,
                                    modifier = Modifier.height(24.dp)
                                )

                            }

                            Text(
                                text = navItem .name,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    }


                )
            }
        }
    }


}
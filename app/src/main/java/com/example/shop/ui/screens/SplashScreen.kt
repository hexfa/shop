package com.example.shop.ui.screens

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shop.R
import com.example.shop.navigation.Screen
import com.example.shop.ui.components.Loading3Dots
import com.example.shop.ui.theme.splashBg
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    val SPLASH_DELAY_MS = 2000L
    val HOME_ROUTE = Screen.Home.route
    SplashContent()
    LaunchedEffect(Unit) {
        waitAndNavigate(SPLASH_DELAY_MS, navController, HOME_ROUTE)
    }
}

private suspend fun waitAndNavigate(
    SPLASH_DELAY_MS: Long,
    navController: NavHostController,
    HOME_ROUTE: String
) {
    delay(SPLASH_DELAY_MS)
    navController.navigate(HOME_ROUTE)
}

@Preview(showBackground = true)
@Composable
fun SplashContent() {
    val fullSize = Modifier.fillMaxSize()
    val CONTENT_PADDING = 20.dp
    Box(
        modifier = fullSize.background(MaterialTheme.colors.splashBg),
        contentAlignment = Alignment.Center
    ) {


        Image(
            painter = painterResource(R.drawable.digi_logo),
            contentDescription = "Shop logo",
            modifier = Modifier.size(250.dp)
        )
    }




    Box(
        modifier = Modifier.fillMaxHeight().padding(CONTENT_PADDING * 5),
        contentAlignment = Alignment.BottomCenter


    ) {
        Image(
            painter = painterResource(R.drawable.digi_txt_white),
            contentDescription = null,
            modifier = Modifier.height(30.dp),
        )

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(CONTENT_PADDING),
        contentAlignment = Alignment.BottomCenter


    ) {

        Loading3Dots(false)

    }
}
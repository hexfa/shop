package com.example.shop.ui.theme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFed1b34)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Colors.splashBg : Color
   @Composable
   get()=Color(0xFFed1b34)


val Colors.selectedBottomBar : Color
   @Composable
   get()=if(isLight) Color(0xFF43474C) else Color(0xFFCFD4DA)


val Colors.unSelectedBottomBar : Color
   @Composable
   
   get()=if(isLight) Color(0xFFA4A1A1) else Color(0xFF575A5E)

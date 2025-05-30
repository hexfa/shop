package com.example.shop.ui.theme

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shop.R


val font_medium= FontFamily(
    Font(R.font.iranyekanmedium)
)

val font_bold= FontFamily(
    Font(R.font.iranyekanbold)
)

val font_standard= FontFamily(
    Font(R.font.iranyekan)
)


val Typography.extraBoldNumber:TextStyle
@Composable
get() = TextStyle(
    fontFamily = font_bold,
    fontWeight = FontWeight.Bold,
    fontSize = 26.sp,

)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = font_medium,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        lineHeight = 25.sp,
        letterSpacing = 0.5.sp
    ),

    body2 = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 25.sp,
        letterSpacing = 0.5.sp
    ),

    h1 =TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 25.sp,

        ),

    h2 =TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 25.sp,

        ),
    h3 =TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 25.sp,

        ),

    h4 =TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 25.sp,

        ),

    h5 =TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 25.sp,

        ),

    h6 =TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 25.sp,

        ),




)
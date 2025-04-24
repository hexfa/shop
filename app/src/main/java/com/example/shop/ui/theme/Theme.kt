package com.example.shop.ui.theme

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Light
private val LightColorPalette = Colors(
    primary            = Color(0xFF673AB7),
    primaryVariant     = Color(0xFF4B2C90),
    secondary          = Color(0xFFFF6F00),
    secondaryVariant   = Color(0xFFC43E00),
    background         = Color(0xFFFFFFFF),
    surface            = Color(0xFFFFFFFF),
    error              = Color(0xFFB00020),
    onPrimary          = Color.White,
    onSecondary        = Color.Black,
    onBackground       = Color.Black,
    onSurface          = Color.Black,
    onError            = Color.White,
    isLight            = true
)

// Dark
private val DarkColorPalette = Colors(
    primary            = Color(0xFF9F7CFF),
    primaryVariant     = Color(0xFFBFA4FF),
    secondary          = Color(0xFFFFB74D),
    secondaryVariant   = Color(0xFFFFCC80),
    background         = Color(0xFF121212),
    surface            = Color(0xFF121212),
    error              = Color(0xFFCF6679),
    onPrimary          = Color.Black,
    onSecondary        = Color.Black,
    onBackground       = Color.White,
    onSurface          = Color.White,
    onError            = Color.Black,
    isLight            = false
)

@RequiresApi(31)
fun dynamicColorsM2(ctx: Context, dark: Boolean): Colors {
    val scheme = if (dark) dynamicDarkColorScheme(ctx)
    else      dynamicLightColorScheme(ctx)

    return Colors(
        primary            = scheme.primary,
        primaryVariant     = scheme.primaryContainer,
        secondary          = scheme.secondary,
        secondaryVariant   = scheme.secondaryContainer,
        background         = scheme.background,
        surface            = scheme.surface,
        error              = scheme.error,
        onPrimary          = scheme.onPrimary,
        onSecondary        = scheme.onSecondary,
        onBackground       = scheme.onBackground,
        onSurface          = scheme.onSurface,
        onError            = scheme.onError,
        isLight            = !dark
    )
}

@Composable
fun ShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val ctx = LocalContext.current

    val colors: Colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            dynamicColorsM2(ctx, darkTheme)

        darkTheme -> DarkColorPalette
        else      -> LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}
package com.example.homestead.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Red400 = Color(0xFFCF6679)

val Light = Color(0xFFEFF6E0)
val LightGreen = Color(0xFFBBCCAF)
val MidGreen = Color(0xFF95A781)
val DarkGreen = Color(0xFF7E9A54)
val LightBrown = Color(0xFF8E7D62)


internal val wearColorPalette: Colors = Colors(
    primary = Light,
    primaryVariant = LightGreen,
    secondary = MidGreen,
    secondaryVariant = DarkGreen,
    error = Red400,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onError = Color.Black
)
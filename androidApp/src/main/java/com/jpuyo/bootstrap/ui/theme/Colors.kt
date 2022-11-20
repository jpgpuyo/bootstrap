package com.jpuyo.bootstrap.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val DarkColors by lazy {
    darkColors(
        primary = Color.Black,
        primaryVariant = Color.Black,
        onPrimary = Color.White,
        secondary = darkCharcoal,
        onSecondary = Color.White,
    )
}

val LightColors by lazy {
    lightColors(
        primary = blueShappire,
        primaryVariant = blueShappire,
        onPrimary = Color.White,
        secondary = Color.White,
        onSecondary = blackOlive,
    )
}

/** Colors **/
private val blueShappire = Color(0xFF1A5D70)
private val maximumBlue = Color(0xFF41B4D2)
private val darkCharcoal = Color(0xFF333333)
private val blackOlive = Color(0xFF3C3C3C)
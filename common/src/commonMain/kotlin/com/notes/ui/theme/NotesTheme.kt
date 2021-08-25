package com.notes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColors = lightColors(
    primary = Color.White,
    primaryVariant = Color(211, 211, 211),
    onPrimary = Color.Black
)

private val darkColors = darkColors(
    primary = Color(39, 39, 39),
    primaryVariant = Color(204, 204, 204),
    onPrimary = Color.White,
    background = Color(18, 18, 18),
    onBackground = Color.White,
    secondary = Color.Red,
    onSecondary = Color.White
)

@Composable
fun NotesTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable (Colors) -> Unit) {
    val colors = if (isDarkTheme) darkColors else lightColors
    MaterialTheme(colors = colors, content = { content(colors) })
}
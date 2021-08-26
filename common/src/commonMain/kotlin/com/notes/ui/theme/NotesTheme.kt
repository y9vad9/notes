package com.notes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
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

private val darkColors = darkColors()

@Composable
fun NotesTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (isDarkTheme) darkColors else lightColors
    MaterialTheme(colors = colors, content = content)
}
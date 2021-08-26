package com.notes.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.arkivanov.decompose.defaultComponentContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.notes.common.DriverFactory
import com.notes.database.NoteDatabase
import com.notes.navigation.NavHostComponent
import com.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = NavHostComponent(NoteDatabase(DriverFactory(this).createDriver()), defaultComponentContext())
        setContent {
            NotesTheme {
                Surface {
                    rememberSystemUiController().apply {
                        val isDarkTheme = isSystemInDarkTheme()
                        val color = if (isDarkTheme)
                            MaterialTheme.colors.surface
                        else MaterialTheme.colors.primary
                        setStatusBarColor(color, !isDarkTheme)
                        setNavigationBarColor(color, !isDarkTheme)
                    }
                    component.render()
                }
            }
        }
    }
}
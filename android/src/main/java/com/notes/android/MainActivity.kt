package com.notes.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.arkivanov.decompose.defaultComponentContext
import com.notes.common.DriverFactory
import com.notes.navigation.NavHostComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val componentContext = defaultComponentContext()
        setContent {
            MaterialTheme {
                Surface {
                    NavHostComponent(NoteDatabase(DriverFactory(this).createDriver()), componentContext).render()
                }
            }
        }
    }
}
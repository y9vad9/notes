package com.notes.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NotesToolbar(title: String) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface
    )
}

@Composable
fun NotesToolbar(
    title: String,
    homeButton: @Composable () -> Unit = {
        Image(Icons.Rounded.ArrowBack, "Go back")
    }
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(title) },
        navigationIcon = homeButton,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface
    )
}
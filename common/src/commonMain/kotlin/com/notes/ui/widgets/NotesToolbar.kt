package com.notes.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NotesToolbar(title: String) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(title) }
    )
}
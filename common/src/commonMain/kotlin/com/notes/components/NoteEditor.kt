package com.notes.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

interface NoteEditor : Component {
    val isSaving: State<Boolean>

    val title: State<String>
    val text: State<String>

    fun processTitle(input: String)
    fun processText(input: String)

    @Composable
    fun save()
}
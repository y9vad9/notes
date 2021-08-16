package com.notes.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.notes.database.Note

interface NotesScreen : ItemModifications {
    val notes: State<List<Note>>
    val isLoading: State<Boolean>

    @Composable
    fun loadNotes()
}

interface ItemModifications {
    fun onItemClicked(id: Long)
    fun onItemAdded(note: Note)
    fun onItemDeleted(id: Long)
}
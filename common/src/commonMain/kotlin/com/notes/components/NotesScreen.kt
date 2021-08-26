package com.notes.components

import androidx.compose.runtime.State
import com.notes.database.Note

interface NotesScreen : ItemModifications {
    val notes: State<List<Note>>
    val isLoading: State<Boolean>

    suspend fun loadNotes()

    fun newItem()
}

interface ItemModifications {
    fun onItemClicked(id: Long)
    fun onItemAdded(note: Note)
    fun onItemDeleted(id: Long)
}
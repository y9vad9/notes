package com.notes.components.integration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.notes.components.Component
import com.notes.components.NotesScreen
import com.notes.database.Note
import com.notes.database.NoteDatabase
import com.notes.ui.screens.NotesView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesScreenComponent(
    componentContext: ComponentContext,
    private val database: NoteDatabase,
    private val gotoNoteEditor: (Note?) -> Unit
) : NotesScreen, Component, ComponentContext by componentContext {
    override val notes: MutableState<List<Note>> = mutableStateOf(listOf())
    override val isLoading: MutableState<Boolean> = mutableStateOf(false)

    override suspend fun loadNotes() = withContext(Dispatchers.Default) {
        isLoading.value = true
        notes.value = database.notesQueries.getAll().executeAsList()
        isLoading.value = false
    }

    override fun newItem() {
        gotoNoteEditor(null)
    }

    override fun onItemClicked(id: Long) {
        gotoNoteEditor(notes.value.first { it.id == id })
    }

    override fun onItemAdded(note: Note) {
        notes.value = notes.value.toMutableList().apply { add(note) }
    }

    override fun onItemDeleted(id: Long) {
        database.notesQueries.remove(id)
        notes.value = notes.value.toMutableList().apply {
            removeAll { it.id == id }
        }
    }

    @Composable
    override fun render() {
        NotesView(this)
    }
}
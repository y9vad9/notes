package com.notes.components.integration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.notes.components.NoteEditor
import com.notes.database.Note
import com.notes.database.NoteDatabase
import com.notes.ui.screens.NoteEditorView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteEditorComponent(private var id: Long?, title: String, text: String, private val database: NoteDatabase) :
    NoteEditor {
    override val isSaving = mutableStateOf(false)
    override val title = mutableStateOf(title)
    override val text = mutableStateOf(text)

    override fun processTitle(input: String) {
        title.value = input
    }

    override fun processText(input: String) {
        text.value = input
    }

    @Composable
    override fun save() {
        isSaving.value = true
        coroutineScope.launch(Dispatchers.Default) {
            if (id == null) {
                database.notesQueries.add(Note(-1, title.value, text.value))
                id = database.notesQueries.find(title.value, text.value)
                    .executeAsOne().id
            } else {
                database.notesQueries.update(title.value, text.value, id!!)
            }
            isSaving.value = false
        }
    }

    @Composable
    override fun render() {
        NoteEditorView(this)
    }
}
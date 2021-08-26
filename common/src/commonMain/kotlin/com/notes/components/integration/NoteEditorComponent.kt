package com.notes.components.integration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.notes.components.NoteEditor
import com.notes.database.NoteDatabase
import com.notes.ui.screens.NoteEditorView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteEditorComponent(
    private var id: Long?,
    title: String,
    text: String,
    private val database: NoteDatabase,
    private val goBack: () -> Unit
) : NoteEditor {
    override val isSaving = mutableStateOf(false)
    override val title = mutableStateOf(title)
    override val text = mutableStateOf(text)

    override fun processTitle(input: String) {
        title.value = input
    }

    override fun processText(input: String) {
        text.value = input
    }

    override fun exit() {
        goBack()
    }

    override suspend fun save(): Unit = withContext(Dispatchers.Default) {
        isSaving.value = true
        if (id == null) {
            database.notesQueries.add(title.value, text.value)
            id = database.notesQueries.find(title.value, text.value)
                .executeAsOne().id
        } else {
            database.notesQueries.update(title.value, text.value, id!!)
        }
        isSaving.value = false
    }

    @Composable
    override fun render() {
        NoteEditorView(this)
    }
}
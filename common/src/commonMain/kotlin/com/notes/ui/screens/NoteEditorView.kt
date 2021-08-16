package com.notes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.notes.components.NoteEditor
import com.notes.ui.widgets.Button
import com.notes.ui.widgets.NotesToolbar

@Composable
fun NoteEditorView(component: NoteEditor) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = { NotesToolbar(component.title.value) }
) {
    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = component.title.value,
            onValueChange = { value -> component.processTitle(value) }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = component.text.value,
            onValueChange = { value -> component.processText(value) }
        )
        SaveButton(
            isSaving = component.isSaving.value,
            onSave = { component.save() }
        )
    }
}

@Composable
private fun SaveButton(isSaving: Boolean, onSave: @Composable () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onSave,
        content = {
            if (isSaving)
                CircularProgressIndicator()
            else Text("Save")
        }
    )
}
package com.notes.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.notes.components.NoteEditor
import com.notes.ui.widgets.NotesToolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun NoteEditorView(component: NoteEditor) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
        NotesToolbar(
            title = component.title.value.takeUnless { it.isEmpty() } ?: "Editor",
            homeButton = {
                IconButton(
                    modifier = Modifier.selectable(true) { component.exit() },
                    content = {
                        Image(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
                        )
                    },
                    onClick = { component.exit() }
                )
            }
        )
    }
) {
    val coroutineScope = rememberCoroutineScope { Dispatchers.Default }
    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            value = component.title.value,
            onValueChange = { value -> component.processTitle(value) },
            singleLine = true,
            placeholder = { Text("Note name") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).weight(1f),
            value = component.text.value,
            onValueChange = { value -> component.processText(value) },
            placeholder = { Text("Text") }
        )
        SaveButton(
            isSaving = component.isSaving.value,
            onSave = { coroutineScope.launch { component.save() } }
        )
    }
}

@Composable
private fun SaveButton(isSaving: Boolean, onSave: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        onClick = onSave,
        content = {
            if (isSaving)
                CircularProgressIndicator()
            else Text("Save")
        }
    )
}
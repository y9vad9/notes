@file:Suppress("unused")

package com.notes.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notes.components.NotesScreen
import com.notes.database.Note
import com.notes.ui.widgets.NotesToolbar

@Composable
fun NotesView(component: NotesScreen) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = { NotesToolbar("Notes") }
) {
    component.loadNotes()
    if (component.isLoading.value)
        LoadingView()
    else ListView(component.notes.value)
}

@Composable
private fun LoadingView() = Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
    CircularProgressIndicator(Modifier.size(100.dp))
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ListView(items: List<Note>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(80.dp)
    ) {
        items(items) { item -> NoteItemView(item) }
    }
}

@Composable
private fun LazyItemScope.NoteItemView(item: Note) = Box(Modifier.fillMaxWidth().padding(8.dp)) {
    Card(Modifier.fillMaxSize().padding(8.dp)) {
        Column {
            Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text(text = item.text)
        }
    }
}
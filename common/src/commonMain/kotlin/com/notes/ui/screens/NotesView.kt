@file:Suppress("unused")

package com.notes.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notes.components.ItemModifications
import com.notes.components.NotesScreen
import com.notes.database.Note
import com.notes.ui.widgets.NotesToolbar

@Composable
fun NotesView(component: NotesScreen) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
        NotesToolbar(
            title = "Notes"
        )
    },
    floatingActionButton = { FAB(onClick = { component.newItem() }) }
) {
    if (component.isLoading.value)
        LoadingView()
    else if (component.notes.value.isEmpty())
        NoItems()
    else ListView(component.notes.value, component)

    LaunchedEffect(true) {
        component.loadNotes()
    }
}

@Composable
private fun LoadingView() = Column(
    Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    CircularProgressIndicator(Modifier.size(50.dp), color = MaterialTheme.colors.secondary)
}

@Composable
private fun NoItems() = Column(
    Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        modifier = Modifier.alpha(0.8f),
        text = "You haven't created any notes yet!"
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ListView(items: List<Note>, handler: ItemModifications) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(180.dp)
    ) {
        items(items) { item ->
            NoteItemView(
                item = item,
                onDeleteClicked = { handler.onItemDeleted(item.id) },
                onClick = {
                    handler.onItemClicked(item.id)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyItemScope.NoteItemView(item: Note, onClick: () -> Unit, onDeleteClicked: () -> Unit) =
    Box(Modifier.fillMaxWidth().padding(12.dp)) {
        val removing = mutableStateOf(false)
        Card(
            modifier = Modifier.fillMaxSize().selectable(true) { onClick() }
                .combinedClickable(
                    enabled = true,
                    onClick = onClick,
                    onLongClick = { removing.value = !removing.value }
                ),
            elevation = 6.dp
        ) {
            if (!removing.value) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.alpha(0.8f),
                        text = item.text,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            } else {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = onDeleteClicked
                    ) {
                        Image(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                        )
                    }
                    IconButton(
                        onClick = { removing.value = false }
                    ) {
                        Image(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                        )
                    }
                }
            }
        }
    }

@Composable
private fun FAB(onClick: () -> Unit) = FloatingActionButton(
    onClick = onClick,
    content = {
        Image(imageVector = Icons.Rounded.Add, "Add note.")
    }
)
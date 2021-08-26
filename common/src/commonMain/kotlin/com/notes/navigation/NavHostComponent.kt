package com.notes.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.push
import com.arkivanov.decompose.router
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.notes.components.Component
import com.notes.components.integration.NoteEditorComponent
import com.notes.components.integration.NotesScreenComponent
import com.notes.database.Note
import com.notes.database.NoteDatabase

class NavHostComponent(
    private val database: NoteDatabase,
    componentContext: ComponentContext
) : Component, ComponentContext by componentContext {

    private val router = router<ScreenConfig, Component>(
        initialConfiguration = ScreenConfig.Notes,
        childFactory = ::createScreenComponent
    )

    /**
     * Factory function to create screen from given ScreenConfig
     */
    private fun createScreenComponent(
        screenConfig: ScreenConfig,
        componentContext: ComponentContext
    ): Component {
        return when (screenConfig) {
            is ScreenConfig.Notes -> NotesScreenComponent(
                componentContext,
                database,
                gotoNoteEditor = this::gotoEditor
            )

            is ScreenConfig.NoteEditor -> NoteEditorComponent(
                screenConfig.id,
                screenConfig.title,
                screenConfig.text,
                database,
                goBack = { router.push(ScreenConfig.Notes) }
            )
            is ScreenConfig.NewNote -> NoteEditorComponent(
                null, "", "", database, goBack = { router.push(ScreenConfig.Notes) }
            )
        }
    }

    private fun gotoEditor(note: Note? = null) {
        if (note == null)
            router.push(ScreenConfig.NewNote)
        else router.push(ScreenConfig.NoteEditor(note.id, note.name, note.text))
    }

    /**
     * Renders screen as per request
     */
    @Composable
    override fun render() {
        Children(routerState = router.state) {
            it.instance.render()
        }
    }

    private sealed class ScreenConfig : Parcelable {
        @Parcelize
        object Notes : ScreenConfig()

        @Parcelize
        object NewNote : ScreenConfig()

        @Parcelize
        data class NoteEditor(val id: Long, val title: String, val text: String) : ScreenConfig()
    }
}
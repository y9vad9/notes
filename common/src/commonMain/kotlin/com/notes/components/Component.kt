package com.notes.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope

interface Component {
    /**
     * Default components [CoroutineScope]'s.
     */
    val coroutineScope: CoroutineScope
        @Composable
        get() = rememberCoroutineScope()

    @Composable
    fun render()
}
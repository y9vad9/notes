import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.notes.common.DriverFactory
import com.notes.navigation.NavHostComponent

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val lifecycleRegistry = LifecycleRegistry()
    val root = NavHostComponent(DriverFactory(), DefaultComponentContext(lifecycleRegistry))
    application {
        val windowState = rememberWindowState()
        Window(
            create = { ComposeWindow() },
            dispose = { }
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                MaterialTheme {
                    root.render()
                }
            }
        }
    }
}
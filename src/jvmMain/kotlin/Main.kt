import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.view.MainView



fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MainView()
    }
}

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.view.MainView
import utils.Requests
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


fun main() = application {

    Requests.get()


    Window(onCloseRequest = ::exitApplication) {
        MainView()
    }
}

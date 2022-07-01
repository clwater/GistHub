import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*
import androidx.compose.ui.ExperimentalComposeUiApi
import ui.view.MainView
import ui.view.TokenDialog
import ui.view.TokenDialogSaveCallBack
import utils.ConfigFile


@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
    Constancts.Gist_Token = ConfigFile.getToken()

    Window(onCloseRequest = ::exitApplication) {
        if (Constancts.Gist_Token.isEmpty()) {
            TokenDialog(object : TokenDialogSaveCallBack {
                override fun save(token: String) {
                    println(token)
                }
            })
        }

        MainView()
    }
}
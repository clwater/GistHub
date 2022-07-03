import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*
import androidx.compose.ui.ExperimentalComposeUiApi
import ui.view.MainView
import ui.view.TokenDialog
import ui.view.TokenDialogSaveCallBack
import utils.ConfigFile
import utils.Requests


@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
	Constancts.Gist_Token = ConfigFile.getToken()
	var showToken by remember { mutableStateOf(Constancts.Gist_Token.isEmpty()) }

	Window(onCloseRequest = ::exitApplication) {
		if (showToken) {
			val saveCallBack = object : TokenDialogSaveCallBack {
				override fun save(token: String) {
					println(token)
				}
			}
			TokenDialog(saveCallBack)
		}
		MainView()
	}
}


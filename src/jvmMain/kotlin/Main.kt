import androidx.compose.ui.window.*
import androidx.compose.ui.ExperimentalComposeUiApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import model.Gists
import ui.view.MainView
import ui.view.TokenDialog
import ui.view.TokenDialogSaveCallBack
import utils.ConfigFile
import utils.Requests


@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
	Constancts.Gist_Token = ConfigFile.getToken()
	Window(onCloseRequest = ::exitApplication) {
		if (Constancts.Gist_Token.isEmpty()) {
			val saveCallBack = object : TokenDialogSaveCallBack {
				override fun save(token: String) {
					ConfigFile.saveToken(token)
				}
			}
			TokenDialog(saveCallBack)
		}
		MainView()
	}
}

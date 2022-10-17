import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import utils.ConfigFile


@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
	Constancts.Gist_Token = ConfigFile.getToken()
	Window(
		onCloseRequest = ::exitApplication,
		title = "GistHub",
		state = WindowState(width = 1280.dp, height = 768.dp),
		icon = BitmapPainter(useResource("ic_launcher.png", ::loadImageBitmap)),
	) {
		MaterialTheme {
			Text("Text")
		}
	}
}

package ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

lateinit var callback: TokenDialogSaveCallBack
interface TokenDialogSaveCallBack {
	fun save(token: String)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TokenDialog(
	callBack: TokenDialogSaveCallBack
) {
	var isShow by remember { mutableStateOf(true) }
	val token = remember { mutableStateOf(TextFieldValue()) }
	val uriHandler = LocalUriHandler.current


	if (isShow) {
		AlertDialog(
			modifier = Modifier.fillMaxWidth(0.8f),
			onDismissRequest = {},
			confirmButton = {
				Button(onClick = {
					isShow = false
					callBack.save(token.value.text)
				}) {
					Text("确认")
				}
			},
			text = {
				Column(modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
					verticalArrangement = Arrangement.Center
					) {
						Text("Token: ")
						TextField(
							value = token.value,
							onValueChange = { token.value = it },
							modifier = Modifier.fillMaxWidth().padding(8.dp)
								.align(Alignment.CenterHorizontally)
						)

					Text("Where is your Token?",
						Modifier.clickable {
							uriHandler.openUri("https://github.com/settings/tokens")
						},
					)
					Text("What is Token?",
						Modifier.clickable {
							uriHandler.openUri("https://docs.github.com/cn/enterprise-server@3.5/developers/apps/building-oauth-apps/scopes-for-oauth-apps")
						},
					)
				}

			}
		)
	}


}
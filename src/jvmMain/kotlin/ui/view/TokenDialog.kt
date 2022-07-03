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
import kotlinx.coroutines.delay
import utils.Requests

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
    var token by remember { mutableStateOf(TextFieldValue()) }
	var status by remember { mutableStateOf(1) }
    val uriHandler = LocalUriHandler.current


    if (isShow) {
        AlertDialog(
            modifier = Modifier.fillMaxWidth(0.8f),
            onDismissRequest = {},
            confirmButton = {
                Button(onClick = {
                    val isSuccess = Requests.checkToken(token.text)
                    if (isSuccess) {
                        isShow = false
                        callBack.save(token.text)
					}else{
						status = 3
					}
                }) {
                    Text(
						text = "save"
					)
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                    verticalArrangement = Arrangement.Center
                ) {
					if (status == 3){
						Text("Token is Error", color = Color.Red)
					}
                    Text("Token: ")
                    TextField(
                        value = token,
                        onValueChange = { token = it },
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        "Where is your Token?",
                        Modifier.clickable {
                            uriHandler.openUri("https://github.com/settings/tokens")
                        },
                    )
                    Text(
                        "What is Token?",
                        Modifier.clickable {
                            uriHandler.openUri("https://docs.github.com/cn/enterprise-server@3.5/developers/apps/building-oauth-apps/scopes-for-oauth-apps")
                        },
                    )
                }

            }
        )
    }


}
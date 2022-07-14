package ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import utils.Requests

interface ReNameDialogSaveCallBack {
    fun save(name: String)
}

class ReNameDialog() {
    private var isShow = mutableStateOf(false)
    private var name = mutableStateOf(TextFieldValue())
    private var isSpace = mutableStateOf(true)


    fun show(_isSpace: Boolean = true, _name: String) {
        isShow.value = true
        isSpace.value = _isSpace
        name.value = TextFieldValue(_name)
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun init(callBack: ReNameDialogSaveCallBack) {
        if (isShow.value) {
            AlertDialog(
                modifier = Modifier.fillMaxWidth(0.8f),
                onDismissRequest = {},
                confirmButton = {
                    Button(onClick = {
                        isShow.value = false
                        callBack.save(name.value.text)
                    }) {
                        Text(
                            text = "save"
                        )
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        isShow.value = false
                    }) {
                        Text(
                            text = "cancel"
                        )
                    }
                },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            value = name.value,
                            onValueChange = {
                                name.value = it
                            },
                            label = {
                                Text(
                                    if (isSpace.value) {
                                        "ReName SpaceName"
                                    } else {
                                        "ReName FileName"
                                    }
                                )
                            },
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                }
            )
        }
    }

}

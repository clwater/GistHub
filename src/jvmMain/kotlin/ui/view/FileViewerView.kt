package ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import enity.Gist_File
import ui.weight.TooltipButton
import viewmodel.FilesViewer

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun FileViewerView(filesViewer: FilesViewer) {
    val canPull = remember {
        mutableStateOf(1)
    }
    val canPush = remember {
        mutableStateOf(1)
    }

    if (filesViewer.files.isEmpty()) {
        EditorEmptyView()
    } else {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(end = 12.dp)) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = filesViewer.spaceName
            )

            Row {
                TooltipButton("pull -f", "重新获取最新Gist, 覆盖当前内容", canPull.value) {
                    canPull.value = 1
                    filesViewer.isChange = false
                    canPush.value = if (filesViewer.isChange){ 1 }else{ 2 }
                }
                TooltipButton("push -f", "提交当前内容到Gist", canPush.value) {
                    println("push -f")
                    filesViewer.isChange = false
                    canPush.value = if (filesViewer.isChange){ 1 }else{ 2 }
                }
                TooltipButton("rename", "重命名Gist") {
                    println("rename")
                    filesViewer.isChange = true
                    canPush.value = if (filesViewer.isChange){ 1 }else{ 2 }
                }

            }
        }

        LazyColumn {
            items(filesViewer.files) {
                if (!it.isSpace) {
                    FileItem(it)
                }
            }
        }
    }
}
@Composable
fun FileItem(gistFile: Gist_File) {
    var isShow by remember { mutableStateOf(true) }
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(12.dp),
                color = Color(0xFF82B1FF),
                text = gistFile.showName
            )

            Row(modifier = Modifier.padding(end = 12.dp), horizontalArrangement = Arrangement.End) {
                Button(
                    modifier = Modifier,
                    onClick = {
                        isShow = !isShow
                    }
                ) {
                    Text(
                        if (isShow) {
                            "hide"
                        } else {
                            "open"
                        }
                    )
                }
            }


        }

        if (isShow) {
            Text(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
//                .padding(12.dp)
                    .border(BorderStroke(2.dp, Color.Red))
                    .padding(12.dp),
                text = gistFile.content
            )
        }
    }
}
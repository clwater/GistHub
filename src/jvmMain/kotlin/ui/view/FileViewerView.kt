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
import utils.ConfigFile
import viewmodel.FilesViewer

val reNameSpaceDialog = ReNameDialog()
val reNameFileDialog = ReNameDialog()

interface GistFileCallBack {
    fun update(gistFile: Gist_File)
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun FileViewerView(filesViewer: FilesViewer) {
    val gistFile = remember {
        mutableStateOf(filesViewer.files)
    }
    val canPull = remember {
        mutableStateOf(1)
    }
    val canPush = remember {
        mutableStateOf(1)
    }
    var spaceName by remember { mutableStateOf(filesViewer.spaceName) }
    var spaceNameChange by remember { mutableStateOf(false) }

    val saveCallBack = object : ReNameDialogSaveCallBack {
        override fun save(name: String) {
            spaceName = name
            spaceNameChange = spaceName != filesViewer.spaceName
            filesViewer.isChange = filesViewer.isChange || spaceNameChange
            canPush.value = if (filesViewer.isChange) {
                1
            } else {
                2
            }
        }
    }

    val gistFileCallBack = object : GistFileCallBack{
        override fun update(gistFile: Gist_File) {
            
        }
    }

    reNameSpaceDialog.init(saveCallBack)



    if (filesViewer.files.isEmpty()) {
        EditorEmptyView()
    } else {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(end = 12.dp)) {
            Text(
                modifier = Modifier.padding(12.dp)
//                    .background(if (spaceNameChange) { Color.Yellow } else { Color.Transparent })
                ,
                text = spaceName
            )

            Row {
                TooltipButton("pull -f", "重新获取最新Gist, 覆盖当前内容", canPull.value) {
                    canPull.value = 1
                    filesViewer.isChange = false
                    canPush.value = if (filesViewer.isChange) {
                        1
                    } else {
                        2
                    }
                }
                TooltipButton("push -f", "提交当前内容到Gist", canPush.value) {
                    println("push -f")
                    filesViewer.isChange = false
                    canPush.value = if (filesViewer.isChange) {
                        1
                    } else {
                        2
                    }
                }
                TooltipButton("rename", "重命名Gist") {
                    reNameSpaceDialog.show(true, spaceName)
                }

            }
        }

        LazyColumn {
            items(filesViewer.files) {
                if (!it.isSpace) {
                    FileItem(it, gistFileCallBack)
                }
            }
        }
    }
}

@Composable
fun FileItem(_gistFile: Gist_File, gistFileCallBack: GistFileCallBack) {
    val gistFile by remember { mutableStateOf(_gistFile) }
    val saveCallBack = object : ReNameDialogSaveCallBack {
        override fun save(name: String) {
            gistFile.showName = name
            gistFileCallBack.update(gistFile)
        }
    }
    reNameFileDialog.init(saveCallBack)
    var isShow by remember { mutableStateOf(true) }
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(12.dp),
                color = Color(0xFF82B1FF),
                text = gistFile.showName
            )

            Row(modifier = Modifier.padding(end = 12.dp), horizontalArrangement = Arrangement.End) {
                TooltipButton("rename", "重命名Gist") {
                    reNameFileDialog.show(false, gistFile.showName)
                }
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
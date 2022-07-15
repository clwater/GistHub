package ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import enity.Gist_File
import ui.weight.TooltipButton
import viewmodel.FilesViewer

var baseFilesViewer by  mutableStateOf(FilesViewer(mutableStateOf(""), mutableListOf()))
var useFilesViewer by  mutableStateOf(FilesViewer(mutableStateOf(""), mutableListOf()))

var isChanged by mutableStateOf(false)
fun checkGistChanges() {
    if (baseFilesViewer.spaceName != useFilesViewer.spaceName) {
        isChanged = true
    }
}

@Composable
fun FileViewerView(filesViewer: FilesViewer){
    baseFilesViewer.files = filesViewer.files
    baseFilesViewer.spaceName.value = filesViewer.spaceName.value
    useFilesViewer.files = filesViewer.files
    useFilesViewer.spaceName.value = filesViewer.spaceName.value

    FileTopView()
    FileBodyView()
}


fun updateFiles(){
    val _spaceName = useFilesViewer.spaceName.value
    useFilesViewer.spaceName.value = ""
    useFilesViewer.spaceName.value = _spaceName
}

@Composable
fun FileTopView() {
    val reNameSpaceDialog = ReNameDialog()
    val saveCallBack = object : ReNameDialogSaveCallBack {
        override fun save(name: String) {
            useFilesViewer.spaceName.value = name
        }
    }
    reNameSpaceDialog.init(saveCallBack)
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(end = 12.dp)) {
            Text(
                modifier = Modifier.padding(12.dp)
                    .background(if (isChanged) { Color.Yellow } else { Color.Transparent })
                ,
                text = useFilesViewer.spaceName.value
            )

            Row {
                TooltipButton("pull -f", "重新获取最新Gist, 覆盖当前内容") {
                    println("pull -f")
                    useFilesViewer.files = baseFilesViewer.files
                    useFilesViewer.spaceName.value = baseFilesViewer.spaceName.value
//                useFilesViewer.spaceName.value = "
                    updateFiles()
                }
                TooltipButton("push -f", "提交当前内容到Gist",
                    if (isChanged){1}else{2}
                ) {
                    println("push -f")
                    isChanged = false
                }
                TooltipButton("rename", "重命名Gist") {
                    reNameSpaceDialog.show(true, useFilesViewer.spaceName.value)
                }

            }
        }
        Text("Add File ToDo")
    }

}
@Composable
fun FileBodyView() {
    Text(useFilesViewer.spaceName.value)

    if (useFilesViewer.files.isEmpty()) {
        EditorEmptyView()
    } else {

        LazyColumn {
            useFilesViewer.files.forEachIndexed() { index, gistFile ->
                item {
                    if (!gistFile.isSpace) {
                        FileItem(gistFile, index)
                    }
                }
            }


        }
    }
}



@Composable
fun FileItem(_gistFile: Gist_File, index: Int) {
    val reNameFileDialog = ReNameDialog()
    val gistFile by remember { mutableStateOf(_gistFile) }

//    println(gistFile.content)
    val saveCallBack = object : ReNameDialogSaveCallBack {
        override fun save(name: String) {
            gistFile.showName.value = name
            useFilesViewer.files[index] = gistFile
        }
    }
    reNameFileDialog.init(saveCallBack)
    var isShow by remember { mutableStateOf(true) }
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(12.dp),
                color = Color(0xFF82B1FF),
                text = gistFile.showName.value
            )

            Row(modifier = Modifier.padding(end = 12.dp), horizontalArrangement = Arrangement.End) {
                TooltipButton("rename", "重命名Gist") {
                    reNameFileDialog.show(false, gistFile.showName.value)
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
                text = gistFile.content.value
            )
        }
    }
}
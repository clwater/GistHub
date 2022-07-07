package ui.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import viewmodel.FilesViewer

@Composable
fun FileViewerView(model: FilesViewer) {
    println("FileViewerView ===============")
    println(model.toString())
    if (model.files.isEmpty()){
        EditorEmptyView()
    }else {
        Text("2", modifier = Modifier)
    }
}
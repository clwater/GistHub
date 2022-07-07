package ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import enity.Gist_File
import viewmodel.FilesViewer

@Composable
fun FileViewerView(model: FilesViewer) {
    if (model.files.isEmpty()){
        EditorEmptyView()
    }else {
        LazyColumn {
            items(model.files){
                if (!it.isSpace) {
                    FileItem(it)
                }
            }
        }
//        Text("2", modifier = Modifier)
    }
}

@Composable
fun FileItem(gistFile: Gist_File){
    Column {
        Text(modifier = Modifier.padding(12.dp)
            ,
            color = Color(0xFF82B1FF),
            text = gistFile.showName
        )
        Text(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
//                .padding(12.dp)
                .border(BorderStroke(2.dp, Color.Red))
                .padding(12.dp)
            ,
            text = gistFile.content
        )
    }
}
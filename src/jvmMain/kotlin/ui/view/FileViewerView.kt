package ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import enity.Gist_File
import viewmodel.FilesViewer
import viewmodel.GistHub

@Composable
fun FileViewerView(filesViewer: FilesViewer) {


    println("FileViewerView:" + filesViewer.spaceName)
    if (filesViewer.files.isEmpty()){
        EditorEmptyView()
    }else {
        Text(modifier = Modifier.padding(12.dp),
            text = filesViewer.spaceName)
        LazyColumn {
            items(filesViewer.files){
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
    var isShow by remember { mutableStateOf(true) }
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(12.dp)
                    ,
                    color = Color(0xFF82B1FF),
                    text = gistFile.showName
                )

            Row(modifier = Modifier.padding(end = 12.dp), horizontalArrangement = Arrangement.End) {
                Button(
                    modifier = Modifier,
                    onClick = {
                        isShow = !isShow
                    }
                ){
                    Text(if (isShow){"close"}else{"open"})
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
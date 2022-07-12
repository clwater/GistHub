package ui.view

import Constancts
import viewmodel.Editors
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import enity.Gist
import utils.Requests
import utils.getAvatarImage
import utils.swapList
import viewmodel.FilesViewer
import viewmodel.GistHub
import viewmodel.Spaces
import kotlin.concurrent.thread

/**
 * Create by clwater on 2022/6/28.
 */
@Preview
@Composable
fun MainView() {
    val gistHub = remember {
        val editors = Editors()
        GistHub(
            editors = editors,
            spaces = Spaces()
        )
    }
    val filesViewer = remember {
        FilesViewer(
            spaceName = "",
            files = mutableListOf()
        )
    }

//    thread {
        gistHub.editors.avatar = getAvatarImage()
    gistHub.spaces.items = Requests.updateAllGis()

        if (gistHub.spaces.items.isNotEmpty()){
            Requests.getGist(gistHub.spaces.items[0].url)
            filesViewer.files = Constancts.filesViewer.files
            filesViewer.spaceName = Constancts.filesViewer.spaceName
        }
//    }
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface{
                //整体左右两部分
                Row {
                    //左侧部分
                    Column(
                        modifier = Modifier.width(300.dp).fillMaxHeight(1f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        //个人信息部分
                        Column(
                            modifier = Modifier.height(300.dp).fillMaxWidth().align(Alignment.CenterHorizontally),
                            verticalArrangement = Arrangement.Center
                        ) {
                            if (gistHub.editors.avatar.isNotEmpty()) {
                                AsyncImage(
                                    load = { loadImageBitmap(gistHub.editors.avatar) },
                                    painterFor = { remember { BitmapPainter(it) } },
                                    contentDescription = "Sample",
                                    modifier = Modifier.width(200.dp).height(200.dp).align(Alignment.CenterHorizontally)
                                        .clip(CircleShape)
                                )
                            }
                            Row {
                                Button(
                                    onClick = {
                                        thread {
                                            gistHub.spaces.items = emptyList()
                                            gistHub.spaces.items = Requests.updateAllGis()
                                        }
                                    }
                                ){
                                    Text("refresh")
                                }
                            }

                        }
                        Column(modifier = Modifier.fillMaxSize(1f)) {
                            //Space部分
                            Column(modifier = Modifier.heightIn(min = 300.dp).weight(0.7f).fillMaxWidth(1f)) {
                                Text("Space")
                                LazyColumn(modifier = Modifier.background(Color.Gray).fillMaxWidth()) {
                                    items(gistHub.spaces.items){
                                        val text = if (it.isFix){
                                            AnnotatedString(it.spaceName)
                                        }else{
                                            AnnotatedString("[未适配] ${it.spaceName}")
                                        }
                                        val url = it.url
                                        val model = it
                                        ClickableText(
                                            onClick = {
                                                model.open()
                                            },
                                            text = text,
                                            modifier = Modifier.padding(8.dp),
                                        )
                                    }
                                }
                            }
                            //Tag部分
                            Column(modifier = Modifier.heightIn(max = 300.dp).weight(0.3f).fillMaxWidth()) {
                                Text("Tag")
                            }
                        }

                    }
                    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
                        FileViewerView(filesViewer)
                    }

                }
            }
        }
    }

}




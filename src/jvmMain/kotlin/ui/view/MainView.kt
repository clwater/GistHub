package ui.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.xml.sax.InputSource
import java.io.File
import java.io.IOException
import java.net.URL

/**
 * Create by clwater on 2022/6/28.
 */
@Preview
@Composable
fun MainView() {

        MaterialTheme {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                //整体左右两部分
                Row {
                    //左侧部分
                    Column(modifier = Modifier.width(300.dp).fillMaxHeight(1f),
                    verticalArrangement = Arrangement.SpaceBetween){
                        //个人信息部分
                        Column(modifier = Modifier.height(300.dp).fillMaxWidth().align(Alignment.CenterHorizontally),
                            verticalArrangement = Arrangement.Center){
                            AsyncImage(
                                load = { loadImageBitmap("https://avatars.githubusercontent.com/u/14257964?v=4") },
                                painterFor = { remember { BitmapPainter(it) } },
                                contentDescription = "Sample",
                                modifier = Modifier.width(200.dp).height(200.dp).align(Alignment.CenterHorizontally)
                                    .clip(CircleShape)
                            )

                        }
                        Column(modifier = Modifier.fillMaxSize(1f)) {
                            //Space部分
                            Column(modifier = Modifier.heightIn(min = 300.dp).weight(0.7f).fillMaxWidth(1f)){
                                Text("Space")
                            }
                            //Tag部分
                            Column(modifier = Modifier.heightIn(max = 300.dp).weight(0.3f).fillMaxWidth()){
                                Text("Tag")
                            }
                        }

                    }
                    Column(modifier = Modifier.background(Color.Green).fillMaxHeight().fillMaxWidth()){
                        Text("2", modifier = Modifier.background(Color.Green))
                    }

                }
            }
        }

}



package ui.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import model.Gists
import utils.Requests
import kotlin.concurrent.thread

/**
 * Create by clwater on 2022/6/28.
 */
@Preview
@Composable
fun MainView() {
    var avatar by remember { mutableStateOf("") }
    thread {
        avatar = getAvatarImage()
    }
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
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
                        if (avatar.isNotEmpty()) {
                            AsyncImage(
                                load = { loadImageBitmap(avatar) },
                                painterFor = { remember { BitmapPainter(it) } },
                                contentDescription = "Sample",
                                modifier = Modifier.width(200.dp).height(200.dp).align(Alignment.CenterHorizontally)
                                    .clip(CircleShape)
                            )
                        }

                    }
                    Column(modifier = Modifier.fillMaxSize(1f)) {
                        //Space部分
                        Column(modifier = Modifier.heightIn(min = 300.dp).weight(0.7f).fillMaxWidth(1f)) {
                            Text("Space")
                        }
                        //Tag部分
                        Column(modifier = Modifier.heightIn(max = 300.dp).weight(0.3f).fillMaxWidth()) {
                            Text("Tag")
                        }
                    }

                }
                Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
                    Text("2", modifier = Modifier)
                }

            }
        }
    }

}


fun getAvatarImage() :String{
    val gists = Requests.gist()
    val listOfPersonsType = Types.newParameterizedType(List::class.java, Gists::class.java)
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val adapter = moshi.adapter<List<Gists>>(listOfPersonsType)
    val persons = adapter.fromJson(gists)
    return persons?.get(0)?.owner?.avatar_url.toString()

}
package ui.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min

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
                        Column(modifier = Modifier.height(300.dp).fillMaxWidth().align(Alignment.CenterHorizontally)
                            .background(Color.Blue)){
                            Text("1111")
                        }
                        Column(modifier = Modifier.fillMaxSize(1f)) {
                            //Space部分
                            Column(modifier = Modifier.heightIn(min = 300.dp).weight(0.7f).fillMaxWidth(1f).background(Color.Red)){
                                Text("Space")
                            }
                            //Tag部分
                            Column(modifier = Modifier.heightIn(max = 300.dp).weight(0.3f).fillMaxWidth().background(Color.Yellow)){
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
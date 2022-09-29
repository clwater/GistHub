import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.Modifier
import enity.GistTitleItem
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import enity.GistInfoItem
import enity.GistTableInfoItem

@Composable
internal fun MainContent(
    modifier: Modifier = Modifier,
    items: List<GistTitleItem>,
    gists: List<GistTableInfoItem>,
    onItemClicked: (id: String) -> Unit,
    spaceName: String,
    chooseId: String,
    //添加item按钮点击
    onAddItemClicked: () -> Unit,
    //右侧title点击
    onSpaceTitleChange: (id: String) -> Unit,
    //右侧Edit点击
    onSpaceEditChange: (id: String, inEdit: Boolean) -> Unit,
    onSpaceClose: (id: String) -> Unit,
    //Space 名称更新
    onSpaceNameChange: (id: String, name: String) -> Unit,

    //保存Space更新
    saveSpaceInfo: (id: String) -> Unit,
) {
    Column() {
        TopAppBar(title = { Text(text = "Todo List") })
        Box(Modifier.weight(1F)) {
            Row {
                Box(modifier = Modifier.width(300.dp)) {
                    ListContent(
                        items = items,
                        onItemClicked = onItemClicked,
                    )

                }
                val inEdit = gists.find { it.id == chooseId } != null && gists.find { it.id == chooseId }!!.inEdit
                Box(modifier = Modifier.padding(12.dp).weight(1f)) {
                    Column {
                        val listState = rememberLazyListState()
                        LazyRow(state = listState) {
                            items(gists) {
                                SpacesTitleBar(
                                    item = it,
                                    onSpaceTitleChange = onSpaceTitleChange,
                                    onSpaceClose = onSpaceClose,
                                )
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            if (inEdit) {

                                var text by remember {
                                    mutableStateOf(
                                        gists.find { it.id == chooseId }!!.spaceName
                                    )
                                }

                                OutlinedTextField(
                                    value = text,
                                    modifier = Modifier
                                        .weight(weight = 1F),
                                    onValueChange = {
                                        text = it
                                        onSpaceNameChange(chooseId, text)
                                    },
                                    label = { Text(text = "Enter Space Name") },
                                )

                            } else {
                                Text(text = spaceName, modifier = Modifier.weight(1f))
                            }

                            Button(
                                modifier = Modifier,
                                enabled = inEdit,
                                onClick = {
                                    saveSpaceInfo(chooseId)
//                                    onSpaceEditChange(chooseId, false)
                                }) {
                                Text("Save")
                            }
                            Button(
                                enabled = !inEdit,
                                onClick = {
                                    onSpaceEditChange(chooseId, true)
                                }) {
                                Text("Edit")
                            }
                        }
                        gists.forEach {
                            if (it.isShow) {
                                GistListContent(items = it.gists)
                            }
                        }
                    }
                }

            }
        }
        Input(onAddClicked = onAddItemClicked)
    }
}

//Space标题栏
@Composable
private fun SpacesTitleBar(
    item: GistTableInfoItem,
    onSpaceTitleChange: (id: String) -> Unit,
    onSpaceClose: (id: String) -> Unit
) = Surface(
    color = if (item.isShow && item.inEdit) {
        Color.Red
    } else if (item.isShow && !item.inEdit) {
        Color.Green
    } else if (!item.isShow && item.inEdit) {
        Color.Blue
    } else {
        Color.Transparent
    }
) {
    Row(
        modifier = Modifier.padding(4.dp)
            .clickable {
                onSpaceTitleChange(item.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (item.inEdit) {
            Text(text = item.spaceName + "(未保存)")
        } else {
            Text(text = item.spaceName)
        }
        Icon(
            Icons.Default.Close,
            tint = LocalContentColor.current,
            contentDescription = "Close",
            modifier = Modifier
                .size(24.dp)
                .padding(4.dp)
                .clickable {
                    onSpaceClose(item.id)
                }
        )
    }
}

@Composable
private fun GistListContent(
    items: List<GistInfoItem>
) {
    Box {
        val listState = rememberLazyListState()
        LazyColumn(state = listState, modifier = Modifier.height(1000.dp)) {
            items(items) { item ->
                GistInfoItem(item = item)
                Divider()
            }

        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(scrollState = listState)
        )
    }
}

@Composable
private fun ListContent(
    items: List<GistTitleItem>,
    onItemClicked: (id: String) -> Unit,

    ) {
    Box {
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            items(items) { item ->
                Item(item = item,
                    onClicked = { onItemClicked(item.id) }
                )
                Divider()
            }

        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(scrollState = listState)
        )
    }
}

@Composable
private fun GistInfoItem(
    item: GistInfoItem
) {
    Text(
        text = item.name,
        modifier = Modifier.height(100.dp)
    )
}

@Composable
private fun Item(
    item: GistTitleItem,
    onClicked: () -> Unit,
) {
    Row(modifier = Modifier.clickable(onClick = onClicked).fillMaxWidth()) {
        Text(
            text = item.name,
            modifier = Modifier.height(100.dp)
        )
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Input(
    onAddClicked: () -> Unit
) {
    IconButton(onClick = onAddClicked) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}
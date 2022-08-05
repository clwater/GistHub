import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import enity.GistTitleItem
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import enity.GistInfoItem
import enity.GistTableInfoItem

@Composable
internal fun MainContent(
    modifier: Modifier = Modifier,
    items : List<GistTitleItem>,
    gists : List<GistTableInfoItem>,
    onItemClicked: (id: String) -> Unit,
    spaceName: String,
    onAddItemClicked: () -> Unit,
){
    Column(){
        TopAppBar(title = { Text(text = "Todo List") })
        Box(Modifier.weight(1F)) {
            Row {
                Box(modifier = Modifier.width(300.dp)){
                    ListContent(
                        items = items,
                        onItemClicked = onItemClicked,
                    )

                }
                Box(modifier = Modifier.padding(12.dp).weight(1f)){
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            Text(text = spaceName, modifier = Modifier.weight(1f))
                            Button(onClick = {}){
                                Text("Edit")
                            }
                        }
                        gists.forEach {
                            if (it.isShow){
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

@Composable
private fun GistListContent(
    items: List<GistInfoItem>
){
    Box{
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            items(items){ item ->
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
    items : List<GistTitleItem>,
    onItemClicked: (id: String) -> Unit,

){
    Box{
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            items(items){ item ->
                Item(item = item,
                    onClicked = {onItemClicked(item.id)}
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
    item : GistInfoItem
){
    Text(text = item.name,
        modifier = Modifier.height(100.dp)
    )
}

@Composable
private fun Item(
    item : GistTitleItem,
    onClicked: () -> Unit,
){
    Row(modifier = Modifier.clickable(onClick = onClicked).fillMaxWidth()){
        Text(text = item.name,
            modifier = Modifier.height(100.dp)
        )
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Input(
    onAddClicked: () -> Unit
){
    IconButton(onClick = onAddClicked) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}
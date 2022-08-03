import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import enity.GistTitleItem
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp

@Composable
internal fun MainContent(
    modifier: Modifier = Modifier,
    items : List<GistTitleItem>,
    onAddItemClicked: () -> Unit,
){
    Column(){
        TopAppBar(title = { Text(text = "Todo List") })
        Box(Modifier.weight(1F)) {
            ListContent(items = items)
        }
        Input(onAddClicked = onAddItemClicked)
    }
}

@Composable
private fun ListContent(
    items : List<GistTitleItem>,

){
    Box{
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            items(items){ item ->
                Item(item = item)
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
private fun Item(
    item : GistTitleItem
){
    Text(text = item.name,
        modifier = Modifier.height(100.dp)
        )
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
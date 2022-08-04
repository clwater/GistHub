package ui.common

import MainContent
import RootStore
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable
fun RootContent(modifier: Modifier = Modifier) {
    val model = remember { RootStore() }
    val state = model.state
    MainContent(
        modifier = modifier,
        items = state.items,
        gists = state.gists,
        spaceName = state.spaceName,
        onAddItemClicked = model::onAddItemClicked,
        onItemClicked = model::onItemClicked,
    )


}
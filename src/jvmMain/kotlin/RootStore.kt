import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import enity.GistTitleItem


internal class RootStore  {
    var state: RootState by mutableStateOf(initialState())
        private set

    data class RootState(
        val items: List<GistTitleItem> = emptyList(),
        val inputText: String = "",
        val editingItemId: Long? = null,
    )


    fun onAddItemClicked() {
        setState {
            val newItem =
                GistTitleItem(
                    name = inputText,
                )

            copy(items = items + newItem, inputText = "")
        }
    }

    private fun initialState(): RootState =
        RootState(
            items = (1L..5L).map { id ->
                GistTitleItem(name = "Some text $id")
            }
        )

    private inline fun setState(update: RootState.() -> RootState) {
        state = state.update()
    }
}
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


internal class RootStore  {
    var state: RootState by mutableStateOf(initialState())
        private set

    data class RootState(
        val items: List<TodoItem> = emptyList(),
        val inputText: String = "",
        val editingItemId: Long? = null,
    )
}
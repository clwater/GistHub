import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import enity.GistInfoItem
import enity.GistTitleItem


internal class RootStore  {
    var state: RootState by mutableStateOf(initialState())
        private set

    data class RootState(
        val items: List<GistTitleItem> = emptyList(),
        val inputText: String = "",
        val chooseId: String? = null,
        val spaceName : String = "",
        val tagList: List<String> = emptyList(),
        val gists : List<GistInfoItem> = emptyList(),
    )

    //获取显示的数据
    //todo 接口获取
    fun getShowGistInfo(id: String): List<GistInfoItem>{
        return arrayListOf(
            GistInfoItem(name = "_name_1  $id",text = "_text_1  $id"),
            GistInfoItem(name = "_name_2  $id",text = "_text_2  $id"),
            GistInfoItem(name = "_name_3  $id",text = "_text_3  $id"),
        )
    }

    fun onItemClicked(id: String) {
        setState {
            copy(chooseId = id, spaceName = "new Choose  $id",
                gists =  getShowGistInfo(id)
                )
        }
    }

    fun onAddItemClicked() {
        setState {
            val newItem =
                GistTitleItem(
                    name = inputText + 1,
                )

            copy(items = items + newItem, inputText = "")
        }
    }

    private fun initialState(): RootState =
        RootState(
            items = arrayListOf(
                GistTitleItem(name = "Some text 1", id = "1") ,
                GistTitleItem(name = "Some text 2", id = "2"),
                GistTitleItem(name = "Some text 3", id = "3"),
                GistTitleItem(name = "Some text 4", id = "4"),
                GistTitleItem(name = "Some text 5", id = "5"),
                GistTitleItem(name = "Some text 6", id = "6"),
                GistTitleItem(name = "Some text 7", id = "7"),
            ),
            spaceName = "_spaceName",
            tagList = arrayListOf("tag1", "tag2"),
            gists =  arrayListOf(
                GistInfoItem(name = "_name_1",text = "_text_1"),
                GistInfoItem(name = "_name_2",text = "_text_2"),
                GistInfoItem(name = "_name_3",text = "_text_3"),
            )
        )

    private inline fun setState(update: RootState.() -> RootState) {
        state = state.update()
    }
}
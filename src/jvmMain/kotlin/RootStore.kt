import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import enity.GistInfoItem
import enity.GistTableInfoItem
import enity.GistTitleItem


internal class RootStore  {
    var state: RootState by mutableStateOf(initialState())
        private set

    data class RootState(
        val items: List<GistTitleItem> = emptyList(),
        val inputText: String = "",
        val chooseId: String = "",
        val spaceName : String = "",
        val tagList: List<String> = emptyList(),
        val gistTableInfo: List<GistTableInfoItem> = emptyList()
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

    //space title 选择
    fun onSpaceTitleChange(id: String){
        setState {
            gistTableInfo.forEach { item ->
                item.isShow = item.id == id
            }
            copy(
                chooseId = id
            )
        }
    }
    fun onSpaceEditChange(id: String, inEdit: Boolean){
        setState {
            gistTableInfo.forEach { item ->
                if (item.id == id){
                    item.inEdit = inEdit
                }
            }
            copy(gistTableInfo = gistTableInfo)
        }
    }

    fun onSpaceClose(id: String){
        setState {
            var chooseId = ""
            if (gistTableInfo.filterNot { it.id == id }.isNotEmpty()){
                chooseId = gistTableInfo.filterNot { it.id == id }[0].id
            }
            onSpaceTitleChange(chooseId)
            copy(
                chooseId = chooseId,
                gistTableInfo = gistTableInfo.filterNot { it.id == id }
                )
        }
    }


    fun onItemClicked(id: String) {
        setState {
            var inCache = false
            gistTableInfo.forEach { item ->
                if (item.id == id){
                    item.isShow = true
                    inCache = true
                }else{
                    item.isShow = false
                }
            }
            if(inCache){
                copy(chooseId = id)
            }else{
                val newItem = GistTableInfoItem(spaceName = "spaceName $id",
                    gists = getShowGistInfo(id),
                    isShow =  true,
                    id = id
                )
                copy(chooseId = id,
                    gistTableInfo = gistTableInfo + newItem
                    )

            }
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
                GistTitleItem(name = "Some text 8", id = "8"),
                GistTitleItem(name = "Some text 9", id = "9"),
                GistTitleItem(name = "Some text 10", id = "10"),
            ),
            spaceName = "_spaceName",
            tagList = arrayListOf("tag1", "tag2"),
        )

    private inline fun setState(update: RootState.() -> RootState) {
        state = state.update()
    }
}
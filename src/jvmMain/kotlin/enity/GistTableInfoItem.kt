package enity

//右侧显示数据使用
internal data class GistTableInfoItem(
    val id: String = "",
    val gists : List<GistInfoItem> = emptyList(),
    var inEdit : Boolean = false,
    var isShow : Boolean = false,
    val spaceName: String = "",
)


package enity

internal data class GistTableInfoItem(
    val id: String = "",
    val gists : List<GistInfoItem> = emptyList(),
    val inEdit : Boolean = false,
    var isShow : Boolean = false,
    val spaceName: String = "",
)

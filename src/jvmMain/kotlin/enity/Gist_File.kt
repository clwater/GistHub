package enity

data class Gist_File(
    var filename:String = "",
    var type:String = "",
    var raw_url:String = "",
    var size:Int = 0,
    var truncated:Boolean = false,
    var content:String = "",
)

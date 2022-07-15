package enity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Gist_File(
    var filename: MutableState<String> = mutableStateOf(""),
    var showName: MutableState<String> = mutableStateOf(""),
    var isSpace: Boolean = false,
    var type:String = "",
    var raw_url:String = "",
    var size:Int = 0,
    var truncated:Boolean = false,
    var content:MutableState<String> = mutableStateOf(""),
    var isLoaclShow:Boolean = true,
    var isChange: Boolean  = false
)

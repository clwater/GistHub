package viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import enity.Gist_File
import utils.Requests

class FilesViewer (
    var spaceName: MutableState<String> = mutableStateOf(""),
    var files: MutableList<Gist_File> = mutableListOf(),
//    var isChange : Boolean = false,
)
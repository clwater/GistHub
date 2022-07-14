package viewmodel

import enity.Gist_File
import utils.Requests

class FilesViewer (
    var spaceName : String,
    var files: List<Gist_File>,
    var isChange : Boolean = false,
){
    fun test(){
        println("text")
    }
}

package viewmodel

import enity.Gist_File

class FilesViewer (
    var spaceName : String,
    var files: List<Gist_File>
){
    fun open( ){
        println("FilesViewer open: $spaceName")
    }
}

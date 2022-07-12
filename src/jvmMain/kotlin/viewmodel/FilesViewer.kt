package viewmodel

import enity.Gist_File
import utils.Requests

class FilesViewer (
    var spaceName : String,
    var files: List<Gist_File>
){
    fun open(url: String){
        val filesViewer = Requests.getGist(url)
        spaceName = filesViewer.spaceName
        files = filesViewer.files
        println("FilesViewer open: $spaceName")
    }
}

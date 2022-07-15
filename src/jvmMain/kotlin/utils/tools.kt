package utils

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import enity.Gist_File
import model.Gists

fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
    clear()
    addAll(newList)
}


fun getSpaceFiles(files: Any) : List<Gist_File>{
    val map = files as Map<*, *>
    val list = mutableListOf<Gist_File>()
    map.mapValues {
        val file = it.value as Map<*, *>
        val gistFile = Gist_File()
        gistFile.filename.value = file["filename"].toString()
        gistFile.type = file["type"].toString()
        gistFile.raw_url = file["raw_url"].toString()
        gistFile.size = (file["size"] as Double).toInt()
        gistFile.truncated = file["truncated"] as Boolean
        gistFile.content.value = file["content"].toString()
        gistFile.showName.value = getShowName(gistFile.filename.value)
        gistFile.isSpace = gistFile.showName.value.isEmpty() || gistFile.showName.value == "null"
        list.add(gistFile)
    }
    return list
}

fun getShowName(name: String): String{
    val parttern = Regex("""\[.*\]""")
    var showName =  parttern.find(name)?.value.toString()
    showName = showName.replace("[", "")
    showName = showName.replace("]", "")
    return showName

}

fun  getSpaceName(files: Any): String{
    val map = files as Map<*, *>
    val pattern = ".* \\[.*\\]".toRegex()
    map.mapValues {
        if (!pattern.containsMatchIn(it.key.toString())) {
            return it.key.toString()
        }
    }
    return ""
}

fun checkIsFix(files: Any): Boolean{
    val map = files as Map<*, *>
    var ifFix = false;
    val pattern = ".* \\[.*\\]".toRegex()
    map.mapValues {
        if (pattern.containsMatchIn(it.key.toString())) {
            ifFix = true
        }
    }
    return ifFix
}

fun getAvatarImage() :String{
    val listOfGistsType = Types.newParameterizedType(List::class.java, Gists::class.java)
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val adapter = moshi.adapter<List<Gists>>(listOfGistsType)
    val gists = adapter.fromJson(Requests.getGistWithIndex(0))
    return gists?.get(0)?.owner?.avatar_url.toString()
}
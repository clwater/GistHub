package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import enity.Gist
import enity.Gist_File

class Spaces {
    var items : List<Gist> by mutableStateOf(emptyList())
}
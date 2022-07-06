package model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Gists(
    @Json(name = "url") val url: String = "",
    @Json(name = "owner") val owner: Gist_Owner,
)
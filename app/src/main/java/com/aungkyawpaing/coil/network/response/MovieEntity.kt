package com.aungkyawpaing.coil.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vincent on 2019-08-20
 */
@JsonClass(generateAdapter = true)
data class MovieEntity(
  @Json(name = "id") val id: Long,
  @Json(name = "backdrop_path") val backDropPath: String?,
  @Json(name = "poster_path") val posterPath: String?,
  @Json(name = "title") val title: String,
  @Json(name = "overview") val overView: String?
)

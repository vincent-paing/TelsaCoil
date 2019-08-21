package com.aungkyawpaing.coil.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vincent on 2019-08-20
 */
@JsonClass(generateAdapter = true)
data class PopularMovieResponse(
  @Json(name = "results") val results: List<MovieEntity>
)
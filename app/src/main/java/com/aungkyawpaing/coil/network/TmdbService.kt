package com.aungkyawpaing.coil.network

import com.aungkyawpaing.coil.network.response.PopularMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Vincent on 2019-08-20
 */
interface TmdbService {

  @GET("movie/top_rated")
  suspend fun getPopularMovies(
    @Query("page") page: Int?
  ): PopularMovieResponse


}
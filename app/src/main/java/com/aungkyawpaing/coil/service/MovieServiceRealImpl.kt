package com.aungkyawpaing.coil.service

import com.aungkyawpaing.coil.model.Movie
import com.aungkyawpaing.coil.network.TmdbService
import javax.inject.Inject

/**
 * Created by Vincent on 2019-08-20
 */
class MovieServiceRealImpl @Inject constructor(
  private val tmdbService: TmdbService
) : com.aungkyawpaing.coil.service.MovieService {

  override suspend fun getPopularMovie(pages: Int): List<Movie> {
    val result = tmdbService.getPopularMovies(pages).results

    return result.map {
      Movie(
        id = it.id,
        title = it.title,
        posterPath = "https://image.tmdb.org/t/p/w500${it.posterPath}"
      )
    }
  }

}
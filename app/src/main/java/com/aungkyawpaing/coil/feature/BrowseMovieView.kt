package com.aungkyawpaing.coil.feature

import com.aungkyawpaing.coil.model.Movie

/**
 * Created by Vincent on 2019-08-20
 */
interface BrowseMovieView {

  fun showMovies(movieList: List<Movie>)

  fun stopPagination()
}
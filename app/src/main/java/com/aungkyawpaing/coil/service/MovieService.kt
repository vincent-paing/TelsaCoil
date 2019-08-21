package com.aungkyawpaing.coil.service

import com.aungkyawpaing.coil.model.Movie

/**
 * Created by Vincent on 2019-08-20
 */
interface MovieService {

  suspend fun getPopularMovie(pages: Int): List<Movie>
}


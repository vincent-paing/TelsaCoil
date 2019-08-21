package com.aungkyawpaing.coil.feature

import androidx.lifecycle.ViewModel
import com.aungkyawpaing.coil.idling.launchIdling
import com.aungkyawpaing.coil.model.Movie
import com.aungkyawpaing.coil.service.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vincent on 2019-08-20
 */
class BrowseMovieViewModel @Inject constructor(
  private val movieService: MovieService
) : ViewModel() {

  private var browseMovieView: BrowseMovieView? = null

  private var page: Int = 1

  private var isLoading = false

  private var isLastPage = false

  private val movieList = mutableListOf<Movie>()

  private val parentJob = Job()
  private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
  protected val scope = CoroutineScope(coroutineContext)

  fun attachView(browseMovieView: BrowseMovieView) {
    this.browseMovieView = browseMovieView
  }

  fun detachView() {
    this.browseMovieView = null
  }

  fun loadNextMovieList() {

    if (!isLoading && !isLastPage) {
      isLoading = true

      scope.launchIdling {
        try {

          val result = movieService.getPopularMovie(page)

          if (result.isEmpty()) {
            isLastPage = true
          }

          movieList.addAll(result)

          page++
          isLoading = false

          withContext(Dispatchers.Main) {
            browseMovieView?.showMovies(movieList.toList())
          }
        } catch (exception: Exception) {
          isLastPage = true
        }
      }
    } else if (isLastPage) {
      browseMovieView?.stopPagination()
    }

  }

}


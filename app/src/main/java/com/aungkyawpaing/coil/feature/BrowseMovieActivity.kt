package com.aungkyawpaing.coil.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import coil.ImageLoader
import com.aungkyawpaing.coil.R.layout
import com.aungkyawpaing.coil.model.Movie
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.rvMovie
import javax.inject.Inject

class BrowseMovieActivity : AppCompatActivity(), BrowseMovieView {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  @Inject lateinit var imageLoader: ImageLoader

  private val movieRecyclerViewAdapter by lazy {
    MovieRecyclerViewAdapter(imageLoader)
  }

  private val browseMovieViewModel: BrowseMovieViewModel by lazy {
    ViewModelProvider(
      this,
      viewModelFactory
    ).get(BrowseMovieViewModel::class.java)
  }

  private val layoutManager by lazy {
    GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
  }

  private val paginationScrollListener = object : OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
      super.onScrolled(recyclerView, dx, dy)

      val visibleItemCount = layoutManager.childCount
      val totalItemCount = layoutManager.itemCount
      val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

      if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
        browseMovieViewModel.loadNextMovieList()
      }

    }

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)


    rvMovie.layoutManager = layoutManager
    rvMovie.adapter = movieRecyclerViewAdapter


    rvMovie.addOnScrollListener(paginationScrollListener)

    browseMovieViewModel.attachView(this)
    browseMovieViewModel.loadNextMovieList()

  }

  override fun showMovies(movieList: List<Movie>) {
    movieRecyclerViewAdapter.submitList(movieList)
  }

  override fun onDestroy() {
    browseMovieViewModel.detachView()
    super.onDestroy()
  }

  override fun stopPagination() {
    rvMovie.removeOnScrollListener(paginationScrollListener)
  }

}

package com.aungkyawpaing.coil.feature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.DefaultRequestOptions
import coil.ImageLoader
import coil.request.LoadRequest
import coil.request.LoadRequestBuilder
import com.aungkyawpaing.coil.R
import com.aungkyawpaing.coil.feature.MovieRecyclerViewAdapter.MovieViewHolder
import com.aungkyawpaing.coil.model.Movie
import kotlinx.android.synthetic.main.item_browse.view.ivPoster
import kotlinx.android.synthetic.main.item_browse.view.tvTitle
import javax.inject.Inject

/**
 * Created by Vincent on 2019-08-20
 */
class MovieRecyclerViewAdapter @Inject constructor(
  private val imageLoader: ImageLoader
) : ListAdapter<Movie, MovieViewHolder>(diffCallBack) {

  companion object {
    private val diffCallBack = object : DiffUtil.ItemCallback<Movie>() {
      override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
      }

    }
  }

  //region RecyclerView Adapter

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_browse, parent, false)
    return MovieViewHolder(itemView, imageLoader)
  }

  override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
    viewHolder.bindMovie(getItem(position))
  }

  //endregion

  //region ViewHolder

  inner class MovieViewHolder(
    itemView: View,
    private val imageLoader: ImageLoader
  ) : RecyclerView.ViewHolder(itemView) {

    private val ivPoster = itemView.ivPoster
    private val tvTitle = itemView.tvTitle

    fun bindMovie(item: Movie) {

      tvTitle.text = item.title

      val loadRequestBuilder = LoadRequestBuilder(
        itemView.context, LoadRequest(
          itemView.context,
          defaults = DefaultRequestOptions()
        )
      )

      loadRequestBuilder.target(ivPoster)

      imageLoader.load(loadRequestBuilder.build())
    }
  }

  //endregion
}
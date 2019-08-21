package com.aungkyawpaing.coil

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import coil.DefaultRequestOptions
import coil.ImageLoader
import coil.request.GetRequest
import coil.request.LoadRequest
import coil.request.Request
import coil.request.RequestDisposable

/**
 * Created by Vincent on 2019-08-21
 */
class FakeImageLoader : ImageLoader {
  val requestList = mutableListOf<Request>()

  val requestSet = mutableSetOf<Request>()

  private val drawable = ColorDrawable(Color.BLACK)

  private val disposable = object : RequestDisposable {
    override fun isDisposed() = true
    override fun dispose() {}
  }

  override val defaults = DefaultRequestOptions()

  override fun load(request: LoadRequest): RequestDisposable {
    // Always call onStart before onSuccess.
    requestList.add(request)
    requestSet.add(request)
    request.target?.onStart(drawable)
    request.target?.onSuccess(drawable)
    return disposable
  }

  override suspend fun get(request: GetRequest): Drawable {
    requestList.add(request)
    requestSet.add(request)
    return drawable
  }

  override fun clearMemory() {}

  override fun shutdown() {}
}
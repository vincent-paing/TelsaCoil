package com.aungkyawpaing.coil.di

import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderBuilder
import com.aungkyawpaing.coil.R
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-08-21
 */
@Module(includes = [ImageLoaderModule.Provider::class ])
abstract class ImageLoaderModule {

  @Module
  object Provider {
    @Provides @JvmStatic @Singleton fun imageLoader(
      context: Context,
      okHttpClient: OkHttpClient
    ): ImageLoader {

      val imageLoaderBuilder = ImageLoaderBuilder(context)

      imageLoaderBuilder.okHttpClient(okHttpClient)
      imageLoaderBuilder.placeholder(R.drawable.placeholder)

      return imageLoaderBuilder.build()
    }
  }

}
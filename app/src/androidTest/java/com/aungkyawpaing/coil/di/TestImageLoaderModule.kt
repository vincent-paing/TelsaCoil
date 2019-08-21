package com.aungkyawpaing.coil.di

import coil.ImageLoader
import com.aungkyawpaing.coil.FakeImageLoader
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-08-21
 */
@Module(includes = [TestImageLoaderModule.Provider::class])
abstract class TestImageLoaderModule {

  @Module
  object Provider {
    @Provides @JvmStatic @Singleton fun imageLoader(): ImageLoader {
//
//      return FakeImageLoader()
      return mock()
    }
  }

}
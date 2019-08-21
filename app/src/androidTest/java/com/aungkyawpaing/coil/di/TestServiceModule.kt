package com.aungkyawpaing.coil.di

import com.aungkyawpaing.coil.service.MovieService
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-08-21
 */
@Module(includes = [TestServiceModule.Provider::class])
abstract class TestServiceModule {

  @Module
  object Provider {

    @Provides @Singleton @JvmStatic fun movieService(): MovieService {
      return mock()
    }

  }

}
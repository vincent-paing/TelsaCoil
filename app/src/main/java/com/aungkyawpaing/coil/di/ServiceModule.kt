package com.aungkyawpaing.coil.di

import com.aungkyawpaing.coil.service.MovieService
import com.aungkyawpaing.coil.service.MovieServiceRealImpl
import dagger.Binds
import dagger.Module

/**
 * Created by Vincent on 2019-08-21
 */
@Module
abstract class ServiceModule {

  @Binds
  abstract fun movieService(movieService: MovieServiceRealImpl): MovieService

}
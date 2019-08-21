package com.aungkyawpaing.coil.di

import android.app.Application
import android.content.Context
import com.aungkyawpaing.coil.di.viewmodel.ViewModelFactoryModule
import com.aungkyawpaing.coil.feature.BrowseMovieModule
import com.aungkyawpaing.coil.network.di.NetworkModule
import dagger.Module
import dagger.Provides

/**
 * Created by Vincent on 2019-08-20
 */

@Module(
  includes = [
    AppModule.Provider::class,
    ViewModelFactoryModule::class,
    ServiceModule::class,
    NetworkModule::class,
    BrowseMovieModule::class,
    ImageLoaderModule::class]
)
abstract class AppModule {

  @Module
  object Provider {

    @Provides @JvmStatic fun context(application: Application): Context {
      return application.applicationContext
    }

  }

}
package com.aungkyawpaing.coil.di

import android.app.Application
import android.content.Context
import com.aungkyawpaing.coil.di.viewmodel.ViewModelFactoryModule
import com.aungkyawpaing.coil.feature.BrowseMovieModule
import com.aungkyawpaing.coil.network.di.NetworkModule
import dagger.Module
import dagger.Provides

/**
 * Created by Vincent on 2019-08-21
 */
@Module(
  includes = [
    AppModule.Provider::class,
    ViewModelFactoryModule::class,
    TestServiceModule::class,
    NetworkModule::class,
    BrowseMovieModule::class,
    TestImageLoaderModule::class]
)
abstract class TestAppModule {

  @Module
  object Provider {

    @Provides @JvmStatic fun context(application: Application): Context {
      return application.applicationContext
    }

  }

}
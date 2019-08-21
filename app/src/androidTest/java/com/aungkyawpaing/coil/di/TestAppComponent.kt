package com.aungkyawpaing.coil.di

import android.app.Application
import coil.ImageLoader
import com.aungkyawpaing.coil.TestApplication
import com.aungkyawpaing.coil.service.MovieService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-08-21
 */
@Singleton
@Component(
  modules = [TestAppModule::class,
    AndroidInjectionModule::class]
)
interface TestAppComponent : AppComponent {

  fun inject(app: TestApplication)

  fun movieService(): MovieService

  fun imageLoader(): ImageLoader

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): TestAppComponent
  }

}

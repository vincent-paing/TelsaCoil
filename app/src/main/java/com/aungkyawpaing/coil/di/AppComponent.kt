package com.aungkyawpaing.coil.di

import android.app.Application
import com.aungkyawpaing.coil.TelsaCoilApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-08-21
 */

@Singleton
@Component(
  modules = [AppModule::class,
    AndroidInjectionModule::class]
)
interface AppComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(application: TelsaCoilApp)

}
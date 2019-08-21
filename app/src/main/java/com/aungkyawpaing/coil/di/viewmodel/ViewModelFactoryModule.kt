package com.aungkyawpaing.coil.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Created by Vincent on 2019-08-08
 */
@Module
abstract class ViewModelFactoryModule {

  @Binds
  abstract fun viewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}
package com.aungkyawpaing.coil.feature

import androidx.lifecycle.ViewModel
import com.aungkyawpaing.coil.di.viewmodel.ViewModelKey
import com.aungkyawpaing.coil.service.MovieService
import com.aungkyawpaing.coil.service.MovieServiceRealImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Vincent on 2019-08-21
 */
@Module
abstract class BrowseMovieModule {

  @ContributesAndroidInjector
  abstract fun browseMovieActivity(): BrowseMovieActivity

  @Binds
  @IntoMap
  @ViewModelKey(BrowseMovieViewModel::class)
  abstract fun browseMovieViewModel(browseMovieViewModel: BrowseMovieViewModel): ViewModel

}
package com.aungkyawpaing.coil

import android.app.Application
import com.aungkyawpaing.coil.di.AppInjector
import com.aungkyawpaing.coil.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Created by Vincent on 2019-08-21
 */
class TelsaCoilApp : Application(), HasAndroidInjector {

  @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): AndroidInjector<Any> {
    return dispatchingAndroidInjector
  }

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent.builder().application(this).build().inject(this)

    AppInjector.initAutoInjection(this)

  }
}

package com.aungkyawpaing.coil

import android.app.Application
import androidx.test.espresso.IdlingRegistry
import androidx.test.platform.app.InstrumentationRegistry
import com.aungkyawpaing.coil.di.AppInjector
import com.aungkyawpaing.coil.di.DaggerTestAppComponent
import com.aungkyawpaing.coil.di.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Created by Vincent on 2019-08-21
 */
class TestApplication : Application(), HasAndroidInjector {

  @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): AndroidInjector<Any> {
    return dispatchingAndroidInjector
  }

  private lateinit var appComponent: TestAppComponent

  companion object {

    fun appComponent(): TestAppComponent {

      return (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApplication).appComponent
    }

  }

  override fun onCreate() {

    IdlingRegistry.getInstance().register(CorutineIdlingResource.getIdlingResource())

    appComponent = DaggerTestAppComponent.builder().application(this).build()

    appComponent.inject(this)

    AppInjector.initAutoInjection(this)
  }

}

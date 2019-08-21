package com.aungkyawpaing.coil.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.android.AndroidInjection

/**
 * Created by Vincent on 2019-08-21
 */
object AppInjector {

  fun initAutoInjection(application: Application) {

    application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
      override fun onActivityPaused(p0: Activity?) {}

      override fun onActivityResumed(p0: Activity?) {}

      override fun onActivityStarted(p0: Activity?) {}

      override fun onActivityDestroyed(p0: Activity?) {}

      override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {}

      override fun onActivityStopped(p0: Activity?) {
      }

      override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        handleActivity(activity)
      }

    })
  }

  private fun handleActivity(activity: Activity) {
    AndroidInjection.inject(activity)

//    if (activity is FragmentActivity) {
//      activity.supportFragmentManager
//        .registerFragmentLifecycleCallbacks(
//          object : FragmentManager.FragmentLifecycleCallbacks() {
//
//            override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
//              super.onFragmentAttached(fm, f, context)
//              if (f is Injectable) {
//                AndroidSupportInjection.inject(f)
//              }
//            }
//
//          }, true
//        )
//    }
  }
}
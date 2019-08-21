package com.aungkyawpaing.coil

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner

/**
 * Created by Vincent on 2019-08-21
 */
class TestRunner : AndroidJUnitRunner() {

  override fun onCreate(arguments: Bundle?) {
    super.onCreate(arguments)
  }

  @Throws(
    InstantiationException::class,
    IllegalAccessException::class,
    ClassNotFoundException::class
  )
  override fun newApplication(
    cl: ClassLoader?,
    className: String?,
    context: Context?
  ): Application {
    return super.newApplication(cl, TestApplication::class.java.name, context)

  }
}
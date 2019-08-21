package com.aungkyawpaing.coil.idling

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Created by Vincent on 2019-08-21
 */

object CorutineIdlingResource {

  private val countingIdlingResource = CountingIdlingResource("COROUTINE")

  fun increment() {
    countingIdlingResource.increment()
  }

  fun decrement() {
    countingIdlingResource.decrement()
  }

  fun getIdlingResource(): IdlingResource = countingIdlingResource

}

fun CoroutineScope.launchIdling(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> Unit
): Job {
  CorutineIdlingResource.increment()
  val job = this.launch(context, start, block)
  job.invokeOnCompletion { CorutineIdlingResource.decrement() }
  return job
}

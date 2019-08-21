package com.aungkyawpaing.coil.feature

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import coil.ImageLoader
import coil.api.load
import coil.request.LoadRequest
import coil.request.RequestDisposable
import com.aungkyawpaing.coil.FakeImageLoader
import com.aungkyawpaing.coil.R
import com.aungkyawpaing.coil.RandomGenerator
import com.aungkyawpaing.coil.RecyclerViewMatcher
import com.aungkyawpaing.coil.TestApplication
import com.aungkyawpaing.coil.model.Movie
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Vincent on 2019-08-21
 */
class BrowseMovieActivityTest {

  @Rule
  @JvmField
  val activityTestRule = ActivityTestRule(BrowseMovieActivity::class.java, true, false)

  lateinit var fakeMovieList: List<Movie>

  lateinit var imageLoader: ImageLoader

  var requestCaptor = argumentCaptor<LoadRequest>()

  @Before
  fun setUp() {
    fakeMovieList = (0..10).map {
      val movie = Movie(
        id = RandomGenerator.randomLong(),
        title = RandomGenerator.randomUuid(),
        posterPath = RandomGenerator.randomUuid()
      )
      movie
    }

    runBlockingTest {
      imageLoader = TestApplication.appComponent().imageLoader()
      whenever(TestApplication.appComponent().movieService().getPopularMovie(any())).thenReturn(
        fakeMovieList
      )

      whenever(imageLoader.load(requestCaptor.capture()))
        .thenAnswer {

          //Set a black color drawable
          requestCaptor.lastValue.target?.onSuccess(ColorDrawable(Color.BLACK))

          object : RequestDisposable {
            override fun isDisposed() = true
            override fun dispose() {}
          }
        }
    }
  }

  @Test
  fun testRecyclerViewShowCorrectly() {

    val intent = Intent(
      InstrumentationRegistry.getInstrumentation().targetContext,
      BrowseMovieActivity::class.java
    )
    activityTestRule.launchActivity(intent)


    fakeMovieList.forEachIndexed { index, movie ->
      onView(withId(R.id.rvMovie)).perform(
        RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
          index
        )
      )
      onView(RecyclerViewMatcher.withRecyclerView(R.id.rvMovie).atPosition(index)).check(
        matches(hasDescendant(withText(movie.title)))
      )


      if (imageLoader is FakeImageLoader) {
        val fakeImageLoader = imageLoader as FakeImageLoader


        verify(fakeImageLoader.load(any(), movie.posterPath, any()), atLeastOnce())
      }
    }
  }
}
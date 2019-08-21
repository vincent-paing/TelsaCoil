package com.aungkyawpaing.coil.network.di

import com.aungkyawpaing.coil.network.TmdbService
import com.aungkyawpaing.coil.network.di.RetrofitModule.Provider
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-08-20
 */
@Module(includes = [Provider::class])
abstract class RetrofitModule {

  @Module
  object Provider {

    @Provides @JvmStatic @Singleton fun retrofit(
      okHttpClient: OkHttpClient
    ): Retrofit {

      val baseUrl = "https://api.themoviedb.org/3/"

      val moshi = Moshi.Builder()
        .build()

      return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
    }

    @Provides @JvmStatic fun movieService(retrofit: Retrofit): TmdbService {
      return retrofit.create(TmdbService::class.java)
    }

  }

}
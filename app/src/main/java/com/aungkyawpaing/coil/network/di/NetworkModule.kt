package com.aungkyawpaing.coil.network.di

import com.aungkyawpaing.coil.BuildConfig
import com.aungkyawpaing.coil.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-08-20
 */
@Module(includes = [NetworkModule.Provider::class, RetrofitModule::class])
abstract class NetworkModule {

  @Module
  object Provider {

    @Provides @JvmStatic @Singleton fun okHttpClient(): OkHttpClient {

      val authInterceptor = AuthInterceptor()

      val okHttpClientBuilder = OkHttpClient.Builder()

      okHttpClientBuilder.addInterceptor(authInterceptor)

      if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
      }

      val okHttpClient = okHttpClientBuilder.build()

      return okHttpClient
    }

  }

}
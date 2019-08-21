package com.aungkyawpaing.coil.network

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by Vincent on 2019-08-20
 */
class AuthInterceptor @Inject constructor() : Interceptor {

  override fun intercept(chain: Chain): Response {
    val request = chain.request()

    val urlBuilder = request.url.newBuilder()

    urlBuilder.addQueryParameter("api_key", "0b8ffb5f410e2624c121cb7c1b63d353")

    val newRequest = request.newBuilder().url(urlBuilder.build()).build()

    return chain.proceed(newRequest)

  }

}



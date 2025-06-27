package com.gonzalomonzon02.movieapp.data.remote.interceptor

import com.gonzalomonzon02.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AddAuthorizationHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
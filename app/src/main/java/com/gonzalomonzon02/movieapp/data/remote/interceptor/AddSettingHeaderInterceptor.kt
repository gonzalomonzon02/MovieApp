package com.gonzalomonzon02.movieapp.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AddSettingHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(newRequest)
    }
}

package com.zachtib.myallowance.api

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpLogger : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        response.header("X-Rate-Limit")?.let { rateLimit ->
            Timber.d("Rate limiting: $rateLimit")
        }
        return response
    }
}
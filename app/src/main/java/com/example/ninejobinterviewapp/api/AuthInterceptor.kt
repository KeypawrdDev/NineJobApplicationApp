package com.example.ninejobinterviewapp.api

import okhttp3.Interceptor
import okhttp3.Response


// Interceptor to add API key to requests
class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        // Get original request and URL
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // Add API key to URL as a query parameter
        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()

        // Create new request with updated URL
        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}

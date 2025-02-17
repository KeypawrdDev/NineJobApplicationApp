package com.example.ninejobinterviewapp.api

import com.example.ninejobinterviewapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    // Fetch latest news articles with query parameters
    @GET("v2/everything")
    suspend fun getLatestNews(
        @Query("q") query: String = "technology",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("pageSize") pageSize: Int = 10,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en"
    ): NewsResponse
}


package com.example.ninejobinterviewapp.data.repository

import com.example.ninejobinterviewapp.api.NewsApiService
import com.example.ninejobinterviewapp.api.RetrofitClient
import com.example.ninejobinterviewapp.data.model.NewsResponse

class NewsRepository(
    private val apiService: NewsApiService = RetrofitClient.api // Inject API service
) {
    suspend fun getLatestNews(page: Int = 1, pageSize: Int = 10): NewsResponse {
        return apiService.getLatestNews(
            page = page,       // Specify the page number
            pageSize = pageSize // Specify the number of articles per page
        )
    }
}


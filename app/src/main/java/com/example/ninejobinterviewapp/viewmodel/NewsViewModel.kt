package com.example.ninejobinterviewapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ninejobinterviewapp.data.model.Article
import com.example.ninejobinterviewapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository = NewsRepository()) : ViewModel() {

    private val _newsState = MutableStateFlow<List<Article>>(emptyList())
    val newsState = _newsState.asStateFlow()

    private var currentPage = 1
    private val pageSize = 10 // Number of articles to fetch per page

    init {
        fetchNews()
    }

    internal fun fetchNews() {
        viewModelScope.launch {
            try {
                // Fetch the latest batch of articles
                val response = repository.getLatestNews(page = currentPage, pageSize = pageSize)

                // Map articles with valid fields
                val articlesWithValidImage = response.articles.map { article ->
                    article.copy(
                        title = article.title ?: "No Title Available",
                        urlToImage = article.urlToImage ?: "",
                    )
                }

                // Append new articles to the existing list of articles
                _newsState.value = _newsState.value + articlesWithValidImage

                // Increment the page for the next fetch
                currentPage++

                Log.d("API_RESPONSE", "Total Articles: ${_newsState.value.size}")

            } catch (e: Exception) {
                Log.e("API_ERROR", "Failed to fetch news: ${e.localizedMessage}")
            }
        }
    }

}


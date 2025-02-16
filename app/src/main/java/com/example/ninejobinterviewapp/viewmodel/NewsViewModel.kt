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

    init {
        fetchNews()
    }

    internal fun fetchNews() {
        viewModelScope.launch {
            try {
                val response = repository.getLatestNews()

                // ✅ Handle case where urlToImage is null
                val articlesWithValidImage = response.articles.map { article ->
                    article.copy(
                        title = article.title ?: "No Title Available", // Default title if null
                        urlToImage = article.urlToImage ?: "", // Default image URL if null
                    )
                }
                _newsState.value = articlesWithValidImage
                Log.d("API_RESPONSE", "Total Articles: ${articlesWithValidImage.size}")

            } catch (e: Exception) {
                Log.e("API_ERROR", "Failed to fetch news: ${e.localizedMessage}")
            }
        }
    }
}


package com.example.ninejobinterviewapp.data.model


data class Article(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val publishedAt: String,
    val url: String
)

data class ArticleResponse(val articles: List<Article>)

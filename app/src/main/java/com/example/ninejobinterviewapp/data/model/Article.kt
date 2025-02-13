package com.example.ninejobinterviewapp.data.model

data class NewsResponse(
    val articles: List<Article>
)

data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,            // ✅ Added URL
    val urlToImage: String?,    // ✅ URL for the article image
    val publishedAt: String,    // ✅ Publication date
    val content: String?        // ✅ Full article content
)

data class Source(
    val id: String?,            // ✅ Source ID
    val name: String            // ✅ Source Name
)

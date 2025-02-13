package com.example.ninejobinterviewapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.ninejobinterviewapp.viewmodel.NewsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsScreen(
    navController: NavController,
    newsViewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val articles by newsViewModel.newsState.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles) { article ->
            val encodedUrl = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
            NewsItem(
                article = article,
                onClick = {
                    // ✅ Navigate with URL-encoded URL
                    navController.navigate("webViewScreen/$encodedUrl")
                }
            )

        }
    }
}

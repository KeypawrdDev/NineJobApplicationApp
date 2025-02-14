package com.example.ninejobinterviewapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.ninejobinterviewapp.ui.NewsItem
import com.example.ninejobinterviewapp.utils.parsePublishedTime
import com.example.ninejobinterviewapp.viewmodel.NewsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsScreen(
    navController: NavController,
    newsViewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val articles by newsViewModel.newsState.collectAsState()
    val allSources = articles.map { it.source.name }.distinct().sorted()
    var selectedSource by remember { mutableStateOf("All Sources") }
    var selectedSortOption by remember { mutableStateOf("Latest") }

    val filteredArticles = articles
        .filter { selectedSource == "All Sources" || it.source.name == selectedSource }
        .sortedBy {
            val date = parsePublishedTime(it.publishedAt)
            if (selectedSortOption == "Latest") -date.time else date.time
        }

    Scaffold(
        topBar = {
            CustomHomeTopBar(
                allSources = allSources,
                selectedSource = selectedSource,
                onSourceSelected = { selectedSource = it },
                selectedSortOption = selectedSortOption,
                onSortSelected = { selectedSortOption = it }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredArticles) { article ->
                    val encodedUrl = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
                    NewsItem(
                        article = article,
                        onClick = { navController.navigate("webViewScreen/$encodedUrl") }
                    )
                }
            }
        }
    }
}

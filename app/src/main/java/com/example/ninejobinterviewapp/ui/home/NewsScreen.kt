package com.example.ninejobinterviewapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ninejobinterviewapp.utils.parsePublishedTime
import com.example.ninejobinterviewapp.viewmodel.NewsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsScreen(
    navController: NavController,  // Navigation controller to manage navigation between screens
    newsViewModel: NewsViewModel = viewModel()  // ViewModel to manage news data
) {
    // Observe the news data state from the ViewModel
    val articles by newsViewModel.newsState.collectAsState()

    // Extract distinct sources from the articles and sort them
    val allSources = articles.map { it.source.name }.distinct().sorted()

    // Remember the selected source and sort option as state variables
    var selectedSource by remember { mutableStateOf("All Sources") }
    var selectedSortOption by remember { mutableStateOf("Latest") }

    // Filter and sort articles based on the selected source and sort option
    val filteredArticles = articles
        .filter { selectedSource == "All Sources" || it.source.name == selectedSource }
        .sortedBy {
            val date = parsePublishedTime(it.publishedAt)  // Convert published date to a Date object
            if (selectedSortOption == "Latest") date.time else -date.time  // Sort by date
        }

    Scaffold(
        topBar = {
            CustomHomeTopBar(
                allSources = allSources,  // Provide the sources to the top bar
                selectedSource = selectedSource,  // Current selected source
                onSourceSelected = { selectedSource = it },  // Update the selected source when changed
                selectedSortOption = selectedSortOption,  // Current sort option
                onSortSelected = { selectedSortOption = it }  // Update the sort option when changed
            )
        }
    ) { paddingValues ->  // Content of the Scaffold
        // A Column to hold the entire content of the screen
        Column(modifier = Modifier
            .fillMaxSize()  // Take full size of the screen
            .padding(paddingValues)) {  // Apply padding passed from Scaffold
            // LazyColumn for displaying the list of articles
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()  // Take full size of the column
                    .testTag("article_list"),  // Set a tag for testing purposes
                contentPadding = PaddingValues(bottom = 16.dp)  // Padding at the bottom of the list
            ) {
                // Loop through filtered articles and create a NewsItem for each
                items(filteredArticles) { article ->
                    val encodedUrl = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())  // Encode the URL
                    NewsItem(
                        article = article,  // Pass the article data to the NewsItem composable
                        onClick = { navController.navigate("webViewScreen/$encodedUrl") }  // Navigate to WebView when clicked
                    )
                }
                // Add an item at the bottom of the list that triggers fetching more news
                item {
                    // If the list is not empty, fetch more news
                    if (filteredArticles.isNotEmpty()) {
                        newsViewModel.fetchNews()  // Trigger fetching more news from the ViewModel
                    }
                }
            }
        }
    }
}

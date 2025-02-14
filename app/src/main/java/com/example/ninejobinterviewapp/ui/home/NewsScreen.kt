package com.example.ninejobinterviewapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    // ✅ Collect unique sources from the articles
    val allSources = articles.map { it.source.name }.distinct().sorted()

    // ✅ States for filter and sorting
    var selectedSource by remember { mutableStateOf("All Sources") }
    var selectedSortOption by remember { mutableStateOf("Latest") }

    // ✅ Apply Filter and Sorting
    val filteredArticles = articles
        .filter { selectedSource == "All Sources" || it.source.name == selectedSource }
        .sortedBy {
            val date = parsePublishedTime(it.publishedAt)
            if (selectedSortOption == "Latest") -date.time else date.time
        }

    Column(modifier = Modifier.fillMaxSize()) {
        // ✅ Filter and Sort Dropdowns
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Source Filter Dropdown
            DropdownMenuComponent(
                label = "Source",
                options = listOf("All Sources") + allSources,
                selectedOption = selectedSource,
                onOptionSelected = { selectedSource = it }
            )

            // Sort Dropdown
            DropdownMenuComponent(
                label = "Sort",
                options = listOf("Latest", "Oldest"),
                selectedOption = selectedSortOption,
                onOptionSelected = { selectedSortOption = it }
            )
        }

        // ✅ Show List of Articles
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredArticles) { article ->
                val encodedUrl = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())

                NewsItem(
                    article = article,
                    onClick = {
                        navController.navigate("webViewScreen/$encodedUrl")
                    }
                )
            }
        }
    }
}


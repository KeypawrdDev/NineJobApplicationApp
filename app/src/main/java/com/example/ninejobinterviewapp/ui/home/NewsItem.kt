package com.example.ninejobinterviewapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ninejobinterviewapp.data.model.Article
import com.example.ninejobinterviewapp.utils.formatPublishedTime


@Composable
fun NewsItem(
    article: Article,
    onClick: () -> Unit
) {
    val formattedTime = formatPublishedTime(article.publishedAt)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (!article.urlToImage.isNullOrEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(article.urlToImage),
                    contentDescription = article.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .testTag("article_title_${article.title.replace(" ", "_")}")  // Replace spaces with underscores
            )

            Text(
                text = "Source: ${article.source.name}",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )

            Text(
                text = "Published: $formattedTime",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}


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
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ninejobinterviewapp.data.model.Article
import com.example.ninejobinterviewapp.utils.formatPublishedTime


@Composable
fun NewsItem(
    article: Article,  // Article object containing information to display
    onClick: () -> Unit  // Callback for handling click events on the NewsItem
) {
    // Format the published time of the article
    val formattedTime = formatPublishedTime(article.publishedAt)

    // Card that contains the entire article item
    Card(
        modifier = Modifier
            .fillMaxWidth()  // Make the card take full width
            .padding(8.dp)  // Add padding around the card
            .clickable(onClick = onClick),  // Make the card clickable and trigger the onClick callback
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)  // Add elevation to create shadow
    ) {
        // Column to arrange the contents of the card vertically
        Column(modifier = Modifier.padding(8.dp)) {
            // Check if the article has an image URL to show the image
            if (!article.urlToImage.isNullOrEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(article.urlToImage),  // Load the image using Coil
                    contentDescription = article.title,  // Provide a description for accessibility
                    contentScale = ContentScale.Crop,  // Crop the image to fill the space
                    modifier = Modifier
                        .fillMaxWidth()  // Make the image take the full width
                        .height(150.dp)  // Set a fixed height for the image
                        .clip(RoundedCornerShape(8.dp))  // Round the corners of the image
                )
            }
            // Display the article's title, with a fallback message if the title is null
            Text(
                text = article.title ?: "No Title Available",  // Fallback title if null
                style = MaterialTheme.typography.headlineSmall,  // Use the headline style
                modifier = Modifier
                    .padding(vertical = 4.dp)  // Add vertical padding around the title
            )

            // Display the article's source name
            Text(
                text = "Source: ${article.source.name}",  // Show the source of the article
                style = MaterialTheme.typography.labelSmall,  // Use a smaller label style
                color = Color.Gray  // Set the text color to gray
            )

            // Display the formatted published date of the article
            Text(
                text = "Published: $formattedTime",  // Show the formatted time
                style = MaterialTheme.typography.labelSmall,  // Use a smaller label style
                color = Color.Gray,  // Set the text color to gray
                modifier = Modifier.padding(top = 2.dp)  // Add top padding
            )
        }
    }
}

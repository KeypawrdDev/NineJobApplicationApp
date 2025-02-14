package com.example.ninejobinterviewapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomWebServiceTopBar(
    title: String,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 12.dp), // ✅ Padding applied to the whole top bar
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // ✅ Back Button
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable { onBackClick() }
        )

        // ✅ Title with Ellipsis and Larger Font Size
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            color = Color.White,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // ✅ Share Button
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Share",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable { onShareClick() }
        )
    }
}

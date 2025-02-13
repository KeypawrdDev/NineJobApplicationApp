package com.example.ninejobinterviewapp.ui.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomHomeTopBar(
    title: String,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    onActionClicked: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp) // Standard height for AppBar
            .background(backgroundColor)
    ) {
        // Title aligned to the center
        Text(
            text = title,
            color = contentColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Center)
        )

        // Optional actions, like a menu icon or other buttons, aligned to the right
        IconButton(
            onClick = onActionClicked,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            // Replace with your icon or image resource
            Icon(
                imageVector = Icons.Filled.MoreVert, // Material icon for more options
                contentDescription = "More options",
                tint = contentColor
            )
        }
    }
}

@Preview
@Composable
fun PreviewCustomTopBar() {
    CustomHomeTopBar(
        title = "Latest News",
        backgroundColor = Color.White,
        contentColor = Color.Black,
        onActionClicked = { /* Handle your action */ }
    )
}

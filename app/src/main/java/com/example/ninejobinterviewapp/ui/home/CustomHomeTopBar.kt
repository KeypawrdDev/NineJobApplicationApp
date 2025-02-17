package com.example.ninejobinterviewapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomHomeTopBar(
    allSources: List<String>,
    selectedSource: String,
    onSourceSelected: (String) -> Unit,
    selectedSortOption: String,
    onSortSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary) // Matching Background Color
            .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 12.dp), // Matching Padding
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Source Filter Dropdown
        DropdownMenuComponent(
            label = "Source",
            options = listOf("All Sources") + allSources,
            selectedOption = selectedSource,
            onOptionSelected = onSourceSelected
        )

        // Sort Dropdown
        DropdownMenuComponent(
            label = "Sort",
            options = listOf("Latest", "Oldest"),
            selectedOption = selectedSortOption,
            onOptionSelected = onSortSelected
        )
    }
}

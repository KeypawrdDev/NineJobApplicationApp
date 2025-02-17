package com.example.ninejobinterviewapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DropdownMenuComponent(
    label: String,  // Label for the dropdown menu
    options: List<String>,  // List of options to display in the dropdown
    selectedOption: String,  // Currently selected option
    onOptionSelected: (String) -> Unit  // Callback to be invoked when an option is selected
) {
    var expanded by remember { mutableStateOf(false) }  // Track the state of the dropdown (expanded or collapsed)

    Box(modifier = Modifier.wrapContentSize(align = Alignment.TopStart)) {
        // Button to trigger the dropdown menu
        TextButton(onClick = { expanded = true }) {
            Text("$label: $selectedOption",  // Display the selected option with the label
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),  // Set the text style
                color = Color.White  // White text color
            )
        }

        // Dropdown menu to show available options
        DropdownMenu(
            expanded = expanded,  // Control visibility of the dropdown
            onDismissRequest = { expanded = false }  // Close the menu when clicked outside
        ) {
            Box(modifier = Modifier.heightIn(max = 250.dp)) {  // Limit the height of the dropdown
                // Scrollable column to hold the options
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    options.forEach { option ->  // Iterate through each option
                        DropdownMenuItem(
                            text = { Text(option) },  // Display the option as text
                            onClick = {
                                onOptionSelected(option)  // Callback for the selected option
                                expanded = false  // Close the dropdown after selection
                            }
                        )
                    }
                }
            }
        }
    }
}

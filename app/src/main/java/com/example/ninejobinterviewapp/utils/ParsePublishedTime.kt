package com.example.ninejobinterviewapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


// Function to parse the published date string into a Date object
fun parsePublishedTime(publishedAt: String): Date {
    return try {
        // Create a SimpleDateFormat to parse the input date string
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        // Set the time zone to UTC for consistent parsing
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        inputFormat.parse(publishedAt) ?: Date()
    } catch (e: Exception) {
        Date()
    }
}
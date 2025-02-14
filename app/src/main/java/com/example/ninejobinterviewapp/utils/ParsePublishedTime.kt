package com.example.ninejobinterviewapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun parsePublishedTime(publishedAt: String): Date {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        inputFormat.parse(publishedAt) ?: Date()
    } catch (e: Exception) {
        Date()
    }
}
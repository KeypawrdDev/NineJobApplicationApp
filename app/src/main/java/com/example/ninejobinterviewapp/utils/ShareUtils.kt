package com.example.ninejobinterviewapp.utils

import android.content.Context
import android.content.Intent

/**
 * Shares a URL using the system share sheet.
 *
 * @param context Context required to launch the share intent.
 * @param url URL to share.
 */
fun shareUrl(context: Context, url: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share News Article")
    context.startActivity(shareIntent)
}

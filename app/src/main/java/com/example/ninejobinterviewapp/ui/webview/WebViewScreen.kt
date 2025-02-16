package com.example.ninejobinterviewapp.ui.webview

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.ninejobinterviewapp.utils.shareUrl
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun WebViewScreen(
    url: String,
    navController: NavController
) {
    var pageTitle by remember { mutableStateOf("Loading...") }
    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null

    // ✅ Decode URL for WebView
    val decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8.toString())

    // ✅ Use Scaffold for Proper Screen Structure
    Scaffold(
        topBar = {
            CustomWebServiceTopBar(
                title = pageTitle,
                onBackClick = { navController.popBackStack() },
                onShareClick = { shareUrl(navController.context, decodedUrl) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // ✅ WebView Container
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView?, url: String?) {
                                pageTitle = view?.title ?: "News Article"
                                backEnabled = view?.canGoBack() ?: false
                            }
                        }
                        settings.apply {
                            javaScriptEnabled = true
                            cacheMode = WebSettings.LOAD_DEFAULT
                        }
                        loadUrl(decodedUrl)
                        webView = this
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        // ✅ Back Handler for WebView Navigation
        BackHandler(enabled = backEnabled) {
            webView?.goBack()
        }
    }
}

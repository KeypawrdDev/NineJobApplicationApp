package com.example.ninejobinterviewapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ninejobinterviewapp.ui.home.NewsScreen
import com.example.ninejobinterviewapp.ui.webview.WebViewScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "newsScreen"
    ) {
        // News List Screen
        composable(route = "newsScreen") {
            NewsScreen(navController = navController)
        }

        // WebView Screen with URL Argument
        composable(
            route = "webViewScreen/{url}",
            arguments = listOf(
                navArgument("url") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            if (url != null) {
                WebViewScreen(url = URLDecoder.decode(url, StandardCharsets.UTF_8.toString()), navController = navController)
            }
        }
    }
}

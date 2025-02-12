package com.example.ninejobinterviewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.ninejobinterviewapp.data.model.Article
import com.example.ninejobinterviewapp.ui.home.HomeScreen
import com.example.ninejobinterviewapp.ui.theme.NineJobInterviewAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val articles = listOf(
            Article(
                title = "Sample Article 1",
                description = "This is a description of the first article.",
                imageUrl = "https://example.com/image1.jpg",
                publishedAt = "2025-02-12",
                url = "https://example.com/article1"
            ),
            // More articles here...
        )

        setContent {
            NineJobInterviewAppTheme {
                val navController = rememberNavController()
                HomeScreen(
                    articles = articles,
                    navController = navController,
                )
//                composable("article_details/{articleUrl}") { backStackEntry ->
//                    val articleUrl = backStackEntry.arguments?.getString("articleUrl")
//                    articleUrl?.let {
//                        ArticleDetailsScreen(url = it)
//                    }
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NineJobInterviewAppTheme {
        Greeting("Android")
    }
}
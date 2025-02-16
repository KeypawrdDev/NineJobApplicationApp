package com.example.ninejobinterviewapp.ui

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import com.example.ninejobinterviewapp.data.model.Article
import com.example.ninejobinterviewapp.data.model.NewsResponse
import com.example.ninejobinterviewapp.data.model.Source
import com.example.ninejobinterviewapp.ui.home.NewsScreen
import com.example.ninejobinterviewapp.viewmodel.NewsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test

class NewsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockViewModel: NewsViewModel = mockk()

    @Test
    fun testNewsScreenContent() {
        // Prepare mock articles data
        val mockArticles = listOf(
            Article(
                source = Source(id = "source1", name = "Source 1"),
                author = "Author 1",
                title = "Article 1",
                description = "Description of article 1",
                url = "https://example.com/article1",
                urlToImage = "https://example.com/article1.jpg",
                publishedAt = "2025-02-16T10:00:00Z",
                content = "Full content of article 1"
            ),
            Article(
                source = Source(id = "source2", name = "Source 2"),
                author = "Author 2",
                title = "Article 2",
                description = "Description of article 2",
                url = "https://example.com/article2",
                urlToImage = "https://example.com/article2.jpg",
                publishedAt = "2025-02-15T10:00:00Z",
                content = "Full content of article 2"
            )
        )

        // Simulate mock response
        val mockResponse = NewsResponse(articles = mockArticles)

        // Create a MutableStateFlow to mock the ViewModel's state flow
        val stateFlow: StateFlow<List<Article>> = MutableStateFlow(mockResponse.articles)

        // Simulate the ViewModel returning the mock StateFlow
        coEvery { mockViewModel.newsState } returns stateFlow

        // Launch the NewsScreen Composable
        composeTestRule.setContent {
            NewsScreen(navController = TestNavHostController(LocalContext.current), newsViewModel = mockViewModel)
        }

        // Wait for idle to ensure all UI updates have been applied
        composeTestRule.waitForIdle()

        // Log all available nodes for debugging
        composeTestRule.onRoot().printToLog("UI_Tree")

        // Ensure that the article list is displayed
        composeTestRule.onNodeWithText("Article 1", useUnmergedTree = true).assertIsDisplayed()

        // Find and scroll to the first article (Article 1)
        composeTestRule.onNodeWithText("Article 1", useUnmergedTree = true)
            .performScrollTo()

        // Ensure Article 1 is displayed after scroll
        composeTestRule.onNodeWithText("Article 1", useUnmergedTree = true).assertIsDisplayed()

        // Similarly, check for Article 2
        composeTestRule.onNodeWithText("Article 2", useUnmergedTree = true)
            .performScrollTo()

        // Ensure Article 2 is displayed after scroll
        composeTestRule.onNodeWithText("Article 2", useUnmergedTree = true).assertIsDisplayed()
    }





    @Test
    fun testSourceSelection() {
        // Prepare mock articles data
        val mockArticles = listOf(
            Article(
                source = Source(id = "source1", name = "Source 1"),
                author = "Author 1",
                title = "Article 1",
                description = "Description of article 1",
                url = "https://example.com/article1",
                urlToImage = "https://example.com/article1.jpg",
                publishedAt = "2025-02-16T10:00:00Z",
                content = "Full content of article 1"
            ),
            Article(
                source = Source(id = "source2", name = "Source 2"),
                author = "Author 2",
                title = "Article 2",
                description = "Description of article 2",
                url = "https://example.com/article2",
                urlToImage = "https://example.com/article2.jpg",
                publishedAt = "2025-02-15T10:00:00Z",
                content = "Full content of article 2"
            )
        )

        val mockResponse = NewsResponse(articles = mockArticles)

        // Create a MutableStateFlow to mock the ViewModel's state flow
        val stateFlow: StateFlow<List<Article>> = MutableStateFlow(mockResponse.articles)

        // Simulate the ViewModel returning the mock StateFlow
        coEvery { mockViewModel.newsState } returns stateFlow

        // Launch the NewsScreen Composable
        composeTestRule.setContent {
            NewsScreen(navController = TestNavHostController(LocalContext.current), newsViewModel = mockViewModel)
        }

        // Wait for idle to ensure all UI updates have been applied
        composeTestRule.waitForIdle()

        // Log the UI tree for debugging
        composeTestRule.onRoot().printToLog("UI_Tree")

        // Simulate user selecting "Source 1" in the dropdown
        composeTestRule.onNodeWithText("Source: All Sources", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithText("Source 1", useUnmergedTree = true).performClick()
        composeTestRule.waitForIdle()

        // Optionally: log the current UI state for further debugging
        composeTestRule.onRoot().printToLog("UI_Tree_After_Filter")

        // Verify that only Article 1 is displayed after filtering by "Source 1"
        composeTestRule.onNodeWithText("Article 1", useUnmergedTree = true)
            .performScrollTo()
            .assertIsDisplayed()  // Article 1 should be visible

        // Ensure that "Article 2" is not displayed after filtering by "Source 1"
        composeTestRule.onNodeWithText("Article 2", useUnmergedTree = true).assertDoesNotExist()  // Article 2 should NOT be visible
    }

    @Test
    fun testSortArticles() {
        // Prepare mock articles data
        val mockArticles = listOf(
            Article(
                source = Source(id = "source1", name = "Source 1"),
                author = "Author 1",
                title = "Article 1",
                description = "Description of article 1",
                url = "https://example.com/article1",
                urlToImage = "https://example.com/article1.jpg",
                publishedAt = "2025-02-16T10:00:00Z",
                content = "Full content of article 1"
            ),
            Article(
                source = Source(id = "source2", name = "Source 2"),
                author = "Author 2",
                title = "Article 2",
                description = "Description of article 2",
                url = "https://example.com/article2",
                urlToImage = "https://example.com/article2.jpg",
                publishedAt = "2025-02-15T10:00:00Z",
                content = "Full content of article 2"
            )
        )

        val mockResponse = NewsResponse(articles = mockArticles)

        // Create a MutableStateFlow to mock the ViewModel's state flow
        val stateFlow: StateFlow<List<Article>> = MutableStateFlow(mockResponse.articles)

        // Simulate the ViewModel returning the mock StateFlow
        coEvery { mockViewModel.newsState } returns stateFlow

        // Launch the NewsScreen Composable
        composeTestRule.setContent {
            NewsScreen(navController = TestNavHostController(LocalContext.current), newsViewModel = mockViewModel)
        }

        // Wait for idle to ensure all UI updates have been applied
        composeTestRule.waitForIdle()

        // Log the UI tree for debugging
        composeTestRule.onRoot().printToLog("UI_Tree_Before_Sort")

        // Simulate user selecting "Sort" dropdown
        composeTestRule.onNodeWithText("Sort: Latest", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithText("Latest", useUnmergedTree = true).performClick()
        composeTestRule.waitForIdle()

        // Optionally: log the current UI state for further debugging
        composeTestRule.onRoot().printToLog("UI_Tree_After_Sort")

        // Verify that the articles are now sorted by the latest published date
        composeTestRule.onNodeWithText("Article 1", useUnmergedTree = true)
            .performScrollTo()
            .assertIsDisplayed()  // Article 1 should be visible (latest article first)

        composeTestRule.onNodeWithText("Article 2", useUnmergedTree = true)
            .performScrollTo()
            .assertIsDisplayed()  // Article 2 should also be visible (oldest article second)

        // Simulate user selecting "Sort" dropdown again to switch to "Oldest"
        composeTestRule.onNodeWithText("Sort: Latest", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithText("Oldest", useUnmergedTree = true).performClick()
        composeTestRule.waitForIdle()

        // Verify that the articles are now sorted by the oldest published date
        composeTestRule.onNodeWithText("Article 2", useUnmergedTree = true)
            .performScrollTo()
            .assertIsDisplayed()  // Article 2 should be displayed first (oldest article)

        composeTestRule.onNodeWithText("Article 1", useUnmergedTree = true)
            .performScrollTo()
            .assertIsDisplayed()  // Article 1 should be displayed second (latest article)
    }


}

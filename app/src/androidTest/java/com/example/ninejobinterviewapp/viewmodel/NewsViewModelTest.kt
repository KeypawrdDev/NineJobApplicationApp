package com.example.ninejobinterviewapp.viewmodel

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import com.example.ninejobinterviewapp.data.model.Article
import com.example.ninejobinterviewapp.data.model.NewsResponse
import com.example.ninejobinterviewapp.data.model.Source
import com.example.ninejobinterviewapp.data.repository.NewsRepository
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coVerify

class NewsViewModelAndroidTest {

    // Ensures LiveData/StateFlow updates happen synchronously during tests
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: NewsRepository = mockk()  // Use mockk to mock NewsRepository
    private val viewModel = NewsViewModel(repository)

    @Test
    fun shouldFetchNewsSuccessfully() = runBlocking {
        // Given
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
            )
        )

        val mockResponse = NewsResponse(articles = mockArticles)

        // Use coEvery to mock the suspend function
        coEvery { repository.getLatestNews() } returns mockResponse

        // When
        viewModel.fetchNews()  // Call the ViewModel method to fetch news

        // Wait for the StateFlow to update and collect the value
        val state = viewModel.newsState.first()  // Use 'first()' to collect the first emitted value

        // Then
        assertEquals(mockArticles, state)  // Verify the articles list is updated correctly

        // Verify that the repository method was called
        coVerify { repository.getLatestNews() }
    }
}

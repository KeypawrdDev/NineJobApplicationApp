# News App

## Description

This app fetches news articles from a public API and displays them in a list. Users can filter articles by source, sort them by the latest or oldest publication dates, and view full articles in a WebView.

## Features

- **Pagination**: Automatically loads more articles as the user scrolls to the bottom of the list. The `NewsViewModel` manages pagination by tracking the current page and fetching the next set of articles when necessary.
- **Sorting and Filtering**: Users can filter articles by source and sort them by publication date (either "Latest" or "Oldest").
- **WebView Integration**: Users can tap on an article to open the full article in a WebView for a detailed view.
- **Error Handling**: If an error occurs when fetching articles, a fallback mechanism is implemented to display a default message or content.

## Architecture

The project follows a **Model-View-ViewModel (MVVM)** architecture pattern.

- **Model**: Represents the data objects and the logic of how the data is fetched (e.g., `Article`, `NewsResponse`).
- **ViewModel**: Manages UI-related data in a lifecycle-conscious way. It communicates with the repository and updates the UI state via `StateFlow` (e.g., `NewsViewModel`).
- **View**: Composables that represent the UI, interact with the ViewModel, and display the data (e.g., `NewsScreen`).

### ViewModel:
The `NewsViewModel` fetches the news data using a repository, manages the state of the app's data, and exposes it to the UI. It handles sorting, filtering, and pagination of news articles.

### Repository:
The `NewsRepository` is responsible for making network requests via `NewsApiService`, which interacts with the remote data source (API).

### UI:
- `NewsScreen`: Displays a list of news articles and allows filtering and sorting.
- `NewsItem`: A single article card showing details like the title, source, image, and publication time.
- `WebViewScreen`: Displays the full content of an article in a WebView when the user taps on an article.

## Test Cases

### `testNewsScreenContent`

**Purpose**: Verifies that the articles are correctly displayed in the `NewsScreen`, and that scrolling works as expected.

**Test Description**:
- **Given**: A list of mock articles and a mock response from the `NewsViewModel`.
- **When**: The `NewsScreen` composable is launched.
- **Then**: The articles should be displayed, and scrolling should allow users to view all articles.

---

### `testSourceSelection`

**Purpose**: Verifies that selecting a source in the dropdown filters the articles accordingly.

**Test Description**:
- **Given**: A list of mock articles with different sources.
- **When**: The user selects a source from the dropdown.
- **Then**: The displayed articles should be filtered to show only articles from the selected source.

---

### `testArticleOrderAfterSorting`

**Purpose**: Verifies that sorting the articles by "Oldest" and "Latest" works correctly.

**Test Description**:
- **Given**: A list of mock articles with different publication dates.
- **When**: The user selects either "Oldest" or "Latest" from the sort dropdown.
- **Then**: The articles should be sorted correctly by publication date. The articles should be reordered based on the selected sort option.

---

### `shouldFetchNewsSuccessfully`

**Purpose**: Verifies that the `NewsViewModel` correctly fetches news from the repository and updates the `StateFlow` with the fetched articles.

**Test Description**:
- **Given**: A list of mock articles and a mock response from the `NewsRepository`.
- **When**: The `fetchNews` function of the `NewsViewModel` is called.
- **Then**: The `StateFlow` should be updated with the mock articles. The repository method `getLatestNews()` should also be verified to ensure it was called.

---

## Code Compilation Instructions

To compile the project, ensure that the following dependencies are included in your `build.gradle` files.

- **Gradle Version**: 8.8.1
- **Kotlin Version**: 2.1.0
- **AGP Version**: 8.8.1

### IDE Requirements:
- **Android Studio** version 2023.1 or higher.
- **Kotlin Plugin**: Version 2.1.0
- **Android Gradle Plugin (AGP)**: Version 8.8.1
- **Compose Plugin**: Version corresponding to the Android Compose BOM.

### Dependency Management:

Use the following dependencies for the project:

```gradle
dependencies {
    implementation 'androidx.navigation:navigation-compose:2.8.7'
    implementation 'io.coil-kt:coil-compose:2.6.0'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'androidx.compose.material3:material3:1.0.0'
    testImplementation 'org.mockito.kotlin:mockito-kotlin:4.1.0'
    testImplementation 'io.mockk:mockk-android:1.12.0'
    androidTestImplementation 'androidx.compose.ui:ui-test-junit4:1.0.0'
}

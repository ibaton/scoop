package se.treehoouse.newsapi.model

import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.Test
import kotlinx.serialization.decodeFromString

import org.junit.Assert.*
import se.treehoouse.newsapi.json

class TestFullSource {
    @ExperimentalSerializationApi
    @Test
    fun unmarshalSource_isCorrect() {
        val testData = """{
            "id": "abc-news",
            "name": "ABC News",
            "description": "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
            "url": "https://abcnews.go.com",
            "category": "general",
            "language": "en",
            "country": "us"
        }"""

        val expectedResult = NewsFullSourceTO(
            id = "abc-news",
            name = "ABC News",
            description = "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
            url = "https://abcnews.go.com",
            category = "general",
            language = "en",
            country = "us"
        )

        assertEquals(expectedResult, json.decodeFromString<NewsFullSourceTO>(testData))
    }

    @ExperimentalSerializationApi
    @Test
    fun unmarshalSource_isInCorrect() {
        val testData = """{
            "id": "svt-news",
            "name": "svt News",
            "description": "Test",
            "url": "https://test.go.com",
            "category": "general",
            "language": "en",
            "country": "us"
        }"""

        val expectedResult = NewsFullSourceTO(
            id = "abc-news",
            name = "ABC News",
            description = "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
            url = "https://abcnews.go.com",
            category = "general",
            language = "en",
            country = "us"
        )

        assertNotEquals(expectedResult, json.decodeFromString<NewsFullSourceTO>(testData))
    }
}
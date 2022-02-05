package se.treehoouse.newsapi.model

import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.Test
import kotlinx.serialization.decodeFromString

import org.junit.Assert.*
import se.treehoouse.newsapi.json

class TestArticle {
    @ExperimentalSerializationApi
    @Test
    fun unmarshalArticle_isCorrect() {
        val testData = """{
            "source": {
                "id": null,
                "name": "New York Times"
            },
            "author": "Jesus Jiménez, Sophie Kasakove",
            "title": "A Tenacious Winter Storm Moves Across the Northeast - The New York Times",
            "description": "The three-day storm left freezing roads, stranded travelers and power outages across a 2,000-mile stretch. The electrical grid held in Texas, but drivers were stuck for 10 hours on an icy highway.",
            "url": "https://www.nytimes.com/2022/02/04/us/winter-storm-snow-ice-northeast.html",
            "urlToImage": "https://static01.nyt.com/images/2022/02/04/us/04winter-storm-1/merlin_201354939_4303f9b9-0636-4ef2-ad6c-cbe92330c18f-facebookJumbo.jpg",
            "publishedAt": "2022-02-04T20:25:56Z",
            "content": "Buffalo, N.Y., and northern Vermont could see snow accumulation of up to 14 inches by Saturday, according to the Weather Service, with six to 12 inches expected across central New Hampshire. New York… [+893 chars]"
        }"""

        val expectedResult = NewsArticleTO(
            source = NewsSourceTO(
                id = null,
                name = "New York Times"
            ),
            author = "Jesus Jiménez, Sophie Kasakove",
            title = "A Tenacious Winter Storm Moves Across the Northeast - The New York Times",
            description = "The three-day storm left freezing roads, stranded travelers and power outages across a 2,000-mile stretch. The electrical grid held in Texas, but drivers were stuck for 10 hours on an icy highway.",
            url = "https://www.nytimes.com/2022/02/04/us/winter-storm-snow-ice-northeast.html",
            urlToImage = "https://static01.nyt.com/images/2022/02/04/us/04winter-storm-1/merlin_201354939_4303f9b9-0636-4ef2-ad6c-cbe92330c18f-facebookJumbo.jpg",
            publishedAt = "2022-02-04T20:25:56Z",
            content = "Buffalo, N.Y., and northern Vermont could see snow accumulation of up to 14 inches by Saturday, according to the Weather Service, with six to 12 inches expected across central New Hampshire. New York… [+893 chars]"
        )

        assertEquals(expectedResult, json.decodeFromString<NewsArticleTO>(testData))
    }

    @ExperimentalSerializationApi
    @Test
    fun unmarshalArticle_isInCorrect() {
        val testData = """{
            "source": {
                "id": null,
                "name": "New York Times"
            },
            "author": "Jesus Jiménez, Sophie Kasakove",
            "title": "A Tenacious Winter Storm Moves Across the Northeast - The New York Times",
            "description": "The three-day storm left freezing roads, stranded travelers and power outages across a 2,000-mile stretch. The electrical grid held in Texas, but drivers were stuck for 10 hours on an icy highway.",
            "url": "https://www.nytimes.com/2022/02/04/us/winter-storm-snow-ice-northeast.html",
            "urlToImage": "https://static01.nyt.com/images/2022/02/04/us/04winter-storm-1/merlin_201354939_4303f9b9-0636-4ef2-ad6c-cbe92330c18f-facebookJumbo.jpg",
            "publishedAt": "2022-02-04T20:25:56Z",
            "content": "Buffalo, N.Y., and northern Vermont could see snow accumulation of up to 14 inches by Saturday, according to the Weather Service, with six to 12 inches expected across central New Hampshire. New York… [+893 chars]"
        }"""

        val expectedResult = NewsArticleTO(
            source = NewsSourceTO(
                id = null,
                name = "Svd"
            ),
            author = "test",
            title = "A Tenacious Winter Storm Moves Across the Northeast - The New York Times",
            description = "The three-day storm left freezing roads, stranded travelers and power outages across a 2,000-mile stretch. The electrical grid held in Texas, but drivers were stuck for 10 hours on an icy highway.",
            url = "https://www.nytimes.com/2022/02/04/us/winter-storm-snow-ice-northeast.html",
            urlToImage = "https://static01.nyt.com/images/2022/02/04/us/04winter-storm-1/merlin_201354939_4303f9b9-0636-4ef2-ad6c-cbe92330c18f-facebookJumbo.jpg",
            publishedAt = "2022-02-04T20:25:56Z",
            content = "Buffalo, N.Y., and northern Vermont could see snow accumulation of up to 14 inches by Saturday, according to the Weather Service, with six to 12 inches expected across central New Hampshire. New York… [+893 chars]"
        )

        assertNotEquals(expectedResult, json.decodeFromString<NewsArticleTO>(testData))
    }
}
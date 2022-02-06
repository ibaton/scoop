package se.treehoouse.newsrepository

import org.junit.Test

import org.junit.Assert.*
import se.treehoouse.newsapi.model.NewsArticleTO
import se.treehoouse.newsapi.model.NewsFullSourceTO
import se.treehoouse.newsapi.model.NewsSourceTO
import se.treehoouse.newsrepository.converters.toModel
import se.treehoouse.newsrepository.model.NewsArticle
import se.treehoouse.newsrepository.model.NewsFullSource
import se.treehoouse.newsrepository.model.NewsSource

class ConvertTransferObjectsTest {

    @Test
    fun convertSource_isCorrect() {
        val expectedSource = NewsSource(
            "Svt",
            "Sveriges television"
        )

        val convertedSource = NewsSourceTO(
            "Svt",
            "Sveriges television"
        ).toModel()

        assertEquals(expectedSource, convertedSource)
    }

    @Test
    fun convertFullSource_isCorrect() {
        val expectedSource = NewsFullSource(
            id = "abc-news",
            name = "ABC News",
            description = "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
            url = "https://abcnews.go.com",
            category = "general",
            language = "en",
            country = "us"
        )

        val convertedSource = NewsFullSourceTO(
            id = "abc-news",
            name = "ABC News",
            description = "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
            url = "https://abcnews.go.com",
            category = "general",
            language = "en",
            country = "us"
        ).toModel()

        assertEquals(expectedSource, convertedSource)
    }

    @Test
    fun convertArticle_isCorrect() {
        val expectedArticle = NewsArticle(
            id = "https://www.nytimes.com/2022/02/04/us/winter-storm-snow-ice-northeast.html".hashCode().toLong(),
            source = NewsSource(
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

        val convertedArticle = NewsArticleTO(
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
        ).toModel()

        assertEquals(expectedArticle, convertedArticle)
    }
}
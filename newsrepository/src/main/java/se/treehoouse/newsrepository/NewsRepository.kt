package se.treehoouse.newsrepository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import se.treehoouse.newsapi.model.Message
import se.treehoouse.newsapi.NewsApiService
import se.treehoouse.newsrepository.converters.*
import se.treehoouse.newsrepository.model.ErrorResult
import se.treehoouse.newsrepository.model.NewsArticle
import se.treehoouse.newsrepository.model.Result
import se.treehoouse.newsstorage.NewsDatabase
import java.lang.Exception

/**
 * Repository responsible for fetching and storing news articles
 */
class NewsRepository(
    private val api: NewsApiService,
    private val db: NewsDatabase
) {

    /**
     * Load provided topics from remote api.
     * Store requested data on device using [NewsDatabase].
     * If device doesn't have a connection return all saved items in database.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadTopics(topics: List<String>): Flow<Result<List<NewsArticle>>> {
        // TODO add pagination
        val filteredTopics = topics.filter { it.isNotBlank() }

        return combine(
            filteredTopics.map { loadRemoteTopic(it) }
        ) { results ->
            val allResults = results.combine()
            if (allResults is Result.Data) {
                // TODO Save result to database async. Requires update loadArticle to be able to handle not saved articles
                db.newsDao().updateAll(
                    allResults.value.toArticlesDb()
                )
            }
            allResults
        }.flatMapLatest { result ->
            // TODO Only return items for provided topics if NoNetworkError
            // TODO Sort database items base on publish date
            when (result) {
                is Result.Data -> flowOf(result)
                is Result.Error -> flowOf(result)
                is Result.NoNetworkError -> db.newsDao().all.map { it.toArticles().toSuccess() }
                is Result.ParsingError -> flowOf(result)
            }
        }
    }

    private fun loadRemoteTopic(topic: String): Flow<Result<List<NewsArticle>>> {
        return flow {
            try {
                val message: Message.ArticleMessageTO = api.everything(query = topic)

                val data: Result.Data<List<NewsArticle>> = message.articles
                    .toArticleModels()
                    .toSuccess()

                emit(data)
            } catch (e: Exception) {
                emit(e.toFailure())
            }
        }
    }

    /**
     * Combine multiple results into single result.
     *
     * @return a combined list with all data on success, error if any result was a failure
     */
    private fun Array<Result<List<NewsArticle>>>.combine(): Result<List<NewsArticle>> {
        return find { it is ErrorResult }
            ?: filterIsInstance<Result.Data<List<NewsArticle>>>()
                .map { it.value }
                .flatten()
                .toSuccess()
    }

    /**
     * Load data for single article.
     */
    fun loadArticle(id: Long): Flow<NewsArticle> {
        // TODO Add error handling
        return db.newsDao().loadNewsArticle(id).map { it.toModel() }
    }
}
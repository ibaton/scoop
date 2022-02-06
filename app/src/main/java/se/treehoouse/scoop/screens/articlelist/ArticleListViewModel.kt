package se.treehoouse.scoop.screens.articlelist

import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import se.treehoouse.scoop.mvi.MviViewModel
import org.orbitmvi.orbit.viewmodel.container
import se.treehoouse.newsrepository.NewsRepository
import se.treehoouse.newsrepository.model.NewsArticle
import se.treehoouse.newsrepository.model.Result
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val newsRepository: NewsRepository,
) : MviViewModel<ArticleListState, ArticleListSideEffect, ArticleListAction>(TAG) {

    override val container = container<ArticleListState, ArticleListSideEffect>(
        ArticleListState(),
    )

    override fun onAction(action: ArticleListAction) {
        when (action) {
            is ArticleListAction.LoadPage -> {
                loadPage()
            }
        }
    }

    private fun loadPage() = viewModelScope.launch {
        newsRepository.loadTopics(listOf("Apple", "Google", "Facebook"))
            .collect { result ->
                if(result is Result.Data<List<NewsArticle>>) {
                    intent {
                        reduce {
                            state.copy(
                                articles = result.value
                            )
                        }
                    }
                }
            }
    }

    companion object {
        const val TAG = "ArticleListViewModel"
    }
}
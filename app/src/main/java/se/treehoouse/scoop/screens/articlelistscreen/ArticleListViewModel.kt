package se.treehoouse.scoop.screens.articlelistscreen

import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import se.treehoouse.scoop.mvi.MviViewModel
import org.orbitmvi.orbit.viewmodel.container
import se.treehoouse.newsrepository.NewsRepository
import se.treehoouse.newsrepository.model.NewsArticle
import se.treehoouse.newsrepository.model.Result
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
            is ArticleListAction.ArticleItemClicked -> navigateToArticle(action.article)
        }
    }

    private fun navigateToArticle(article: NewsArticle) = intent{
        postSideEffect(ArticleListSideEffect.NavigateToArticle(article))
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
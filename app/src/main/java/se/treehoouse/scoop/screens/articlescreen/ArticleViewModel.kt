package se.treehoouse.scoop.screens.articlescreen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import se.treehoouse.newsrepository.NewsRepository
import se.treehoouse.scoop.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : MviViewModel<ArticleState, ArticleSideEffect, ArticleAction>(TAG) {

    override val container = container<ArticleState, ArticleSideEffect>(
        ArticleState(),
    )

    override fun onAction(action: ArticleAction) {
        when (action) {
            is ArticleAction.LoadPage -> {
                loadPage()
            }
        }
    }

    private fun loadPage() = viewModelScope.launch {

    }

    companion object {
        const val TAG = "ArticleViewModel"
    }
}
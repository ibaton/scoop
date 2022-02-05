package se.treehoouse.scoop.screens.articlelist

import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import se.treehoouse.scoop.mvi.MviViewModel
import org.orbitmvi.orbit.viewmodel.container
import se.treehoouse.newsrepository.NewsRepository
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

    }

    companion object {
        const val TAG = "ArticleListViewModel"
    }
}
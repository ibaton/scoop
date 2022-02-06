package se.treehoouse.scoop.screens.articlescreen

import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import se.treehoouse.scoop.mvi.MviViewModel
import org.orbitmvi.orbit.viewmodel.container
import se.treehoouse.newsrepository.NewsRepository
import se.treehoouse.newsrepository.model.NewsArticle
import se.treehoouse.newsrepository.model.Result
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    @ApplicationContext val context: Context,
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
        const val TAG = "ArticleListViewModel"
    }
}
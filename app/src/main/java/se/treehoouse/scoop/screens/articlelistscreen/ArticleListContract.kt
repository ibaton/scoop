package se.treehoouse.scoop.screens.articlelistscreen

import se.treehoouse.newsrepository.model.NewsArticle

// TODO Add error state
sealed class ArticleListState {
    data class DataState(
        val articles: List<NewsArticle> = emptyList()
    ) : ArticleListState()

    object ErrorState : ArticleListState()
    object LoadingState : ArticleListState()
}

sealed class ArticleListSideEffect {
    data class ToastEffect(val text: String) : ArticleListSideEffect()
    data class NavigateToArticle(val article: NewsArticle) : ArticleListSideEffect()
}

sealed class ArticleListAction {
    object LoadPage : ArticleListAction()

    data class ArticleItemClicked(
        val article: NewsArticle
    ) : ArticleListAction()
}
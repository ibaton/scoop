package se.treehoouse.scoop.screens.articlelistscreen

import se.treehoouse.newsrepository.model.NewsArticle

data class ArticleListState(
    val articles: List<NewsArticle> = emptyList()
)

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
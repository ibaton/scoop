package se.treehoouse.scoop.screens.articlelist

import se.treehoouse.newsrepository.model.NewsArticle

data class ArticleListState(
    val articles: List<NewsArticle> = emptyList()
)

sealed class ArticleListSideEffect {
    data class ToastEffect(val text: String) : ArticleListSideEffect()
}

sealed class ArticleListAction {
    object LoadPage : ArticleListAction()
}
package se.treehoouse.scoop.screens.articlelist

import se.treehoouse.newsrepository.model.NewsSource

data class ArticleListState(
    val articles: List<NewsSource> = emptyList()
)

sealed class ArticleListSideEffect {
    data class ToastEffect(val text: String) : ArticleListSideEffect()
    object FinishEffect : ArticleListSideEffect()
}

sealed class ArticleListAction {
    object LoadPage : ArticleListAction()
}
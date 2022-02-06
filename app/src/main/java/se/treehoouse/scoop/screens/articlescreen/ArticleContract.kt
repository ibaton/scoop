package se.treehoouse.scoop.screens.articlescreen

import se.treehoouse.newsrepository.model.NewsArticle

// TODO add error state
data class ArticleState(
    val article: NewsArticle? = null
)

sealed class ArticleSideEffect {
    data class ToastEffect(val text: String) : ArticleSideEffect()
}

sealed class ArticleAction {
    data class LoadPage(val id: Long) : ArticleAction()
}
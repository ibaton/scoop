package se.treehoouse.scoop.screens.articlescreen

import se.treehoouse.newsrepository.model.NewsArticle

data class ArticleState(
    val article: NewsArticle? = null
)

sealed class ArticleSideEffect {
    data class ToastEffect(val text: String) : ArticleSideEffect()
}

sealed class ArticleAction {
    object LoadPage : ArticleAction()
}
package se.treehoouse.scoop.screens.articlelistscreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import se.treehoouse.scoop.screens.articlelistscreen.views.ArticleItemView

@Composable
fun ArticleListScreen(
    navController: NavController,
    viewModel: ArticleListViewModel = hiltViewModel(),
) {
    val state: ArticleListState = viewModel.container.stateFlow
        .collectAsState().value

    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        viewModel.postAction(ArticleListAction.LoadPage)
        launch {
            viewModel.container.sideEffectFlow
                .collect { handleSideEffect(context, navController, it) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        for (article in state.articles) {
            ArticleItemView(article) {
                viewModel.postAction(ArticleListAction.ArticleItemClicked(it))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private fun toast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

private fun handleSideEffect(
    context: Context,
    navController: NavController,
    sideEffect: ArticleListSideEffect
) {
    when (sideEffect) {
        is ArticleListSideEffect.ToastEffect -> toast(context, sideEffect.text)
        is ArticleListSideEffect.NavigateToArticle -> navController.navigate("article/articleId")
    }
}
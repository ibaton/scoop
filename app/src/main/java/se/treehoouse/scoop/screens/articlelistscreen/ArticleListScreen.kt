package se.treehoouse.scoop.screens.articlelistscreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import se.treehoouse.scoop.R
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

    when (state) {
        is ArticleListState.DataState -> DataView(state, viewModel)
        ArticleListState.ErrorState -> ErrorView()
        ArticleListState.LoadingState -> LoadingView()
    }
}

@Composable
private fun DataView(
    state: ArticleListState.DataState,
    viewModel: ArticleListViewModel
) {
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

@Composable
private fun ErrorView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.something_went_wrong),
            color = MaterialTheme.colors.error
        )
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
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
        is ArticleListSideEffect.NavigateToArticle -> navController.navigate("article/${sideEffect.article.id}")
    }
}
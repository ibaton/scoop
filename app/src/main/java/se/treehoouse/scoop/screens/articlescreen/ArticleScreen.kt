package se.treehoouse.scoop.screens.articlescreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import se.treehoouse.scoop.screens.articlescreen.views.ArticleView

@Composable
fun ArticleScreen(
    articleId: Long,
    viewModel: ArticleViewModel = hiltViewModel(),
) {
    val state: ArticleState = viewModel.container.stateFlow
        .collectAsState().value

    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        viewModel.postAction(ArticleAction.LoadPage(articleId))
        launch {
            viewModel.container.sideEffectFlow
                .collect { handleSideEffect(context, it) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if(state.article != null) {
            ArticleView(state.article)
        }
    }
}

private fun toast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

private fun handleSideEffect(
    context: Context,
    sideEffect: ArticleSideEffect
) {
    when (sideEffect) {
        is ArticleSideEffect.ToastEffect -> toast(context, sideEffect.text)
    }
}
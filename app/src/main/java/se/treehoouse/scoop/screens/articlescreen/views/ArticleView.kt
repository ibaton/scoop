package se.treehoouse.scoop.screens.articlescreen.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import se.treehoouse.newsrepository.model.NewsArticle

@Composable
fun ArticleView(
    article: NewsArticle,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (article.urlToImage != null) {
            Image(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                painter = rememberImagePainter(data = article.urlToImage),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val source = article.source
            if (source.name.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = source.name,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            val publishedAt = article.publishedAt
            if (publishedAt != null) {
                Text(
                    text = publishedAt,
                    style = MaterialTheme.typography.body2,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.h5,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.content,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}
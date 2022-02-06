package se.treehoouse.scoop.screens.articlelistscreen.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import se.treehoouse.newsrepository.model.NewsArticle

@Composable
fun ArticleItemView(
    article: NewsArticle,
    onClick: (NewsArticle)->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(article) }
            .padding(16.dp),
    ) {
        if(article.urlToImage != null) {
            Image(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp)),
                painter = rememberImagePainter(data = article.urlToImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        val source = article.source
        if(source.name.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = source.name,
                style = MaterialTheme.typography.subtitle1,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.title,
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.height(6.dp))
        val publishedAt = article.publishedAt
        if(publishedAt != null) {
            // TODO convert date string to localized readable format
            // TODO Make text color less distinct
            Text(
                text = publishedAt,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}
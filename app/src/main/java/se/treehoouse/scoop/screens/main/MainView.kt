package se.treehoouse.scoop.screens.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import se.treehoouse.scoop.screens.articlelist.ArticleListView
import se.treehoouse.scoop.ui.theme.ScoopTheme
import androidx.hilt.navigation.compose.hiltViewModel
import se.treehoouse.scoop.screens.articlelist.ArticleListViewModel

@Composable
fun MainView() {
    ScoopTheme {
        Scaffold {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "articleList") {
                composable("articleList") { ArticleListView() }
                composable("article/{articleId}") { ArticleListView() }
            }
        }
    }
}
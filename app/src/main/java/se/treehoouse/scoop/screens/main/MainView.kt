package se.treehoouse.scoop.screens.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import se.treehoouse.scoop.screens.articlelistscreen.ArticleListScreen
import se.treehoouse.scoop.screens.articlescreen.ArticleScreen
import se.treehoouse.scoop.ui.theme.ScoopTheme

@Composable
fun MainView() {
    ScoopTheme {
        Scaffold {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "articleList") {
                composable("articleList") {
                    ArticleListScreen(
                        navController = navController
                    )
                }
                composable(
                    "article/{articleId}",
                    arguments = listOf(navArgument("articleId") { type = NavType.LongType })
                ) { backStackEntry ->
                    ArticleScreen(
                        articleId = backStackEntry.arguments!!.getLong("articleId"),
                    )
                }
            }
        }
    }
}
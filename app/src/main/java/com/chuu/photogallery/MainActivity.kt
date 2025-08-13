package com.chuu.photogallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chuu.photogallery.photo_detail_screen.PhotoDetailScreen
import com.chuu.photogallery.photo_list_screen.PhotoListScreen
import com.chuu.photogallery.ui.theme.PhotoGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "list-screen",
                        enterTransition = {
                            slideInHorizontally(initialOffsetX = { it })
                        },
                        exitTransition = {
                            slideOutHorizontally(targetOffsetX = { it })
                        }
                    ) {
                        composable("list-screen") {
                            PhotoListScreen(
                                onItemClick = { url, title ->
                                    navController.navigate("detail/${url}/${title}")
                                }
                            )
                        }

                        composable(
                            route = "detail/{url}/{title}", // {userId} is the placeholder
                            arguments = listOf(
                                navArgument("url") { type = NavType.StringType },
                                navArgument("title") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            // Retrieve the argument in the destination composable
                            val url = backStackEntry.arguments?.getString("url")
                            val title = backStackEntry.arguments?.getString("title")
                            PhotoDetailScreen(
                                imageUrl = url ?: "",
                                title = title ?: ""
                            )
                        }
                    }
                   PhotoListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhotoGalleryTheme {
        Greeting("Android")
    }
}
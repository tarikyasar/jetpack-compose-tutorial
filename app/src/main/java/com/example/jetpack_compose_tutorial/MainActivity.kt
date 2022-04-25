package com.example.jetpack_compose_tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpack_compose_tutorial.data.Movie
import com.example.jetpack_compose_tutorial.navigation.Screen
import com.example.jetpack_compose_tutorial.ui.theme.JetpackComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            val movies = getMovies(context)

            JetpackComposeTutorialTheme {
                Surface(
                    color = Color.White,
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MovieList.route
                    ) {
                        composable(Screen.MovieList.route) {
                            MovieListScreen(movies = movies, navController = navController)
                        }

                        composable(
                            route = "movieDetail/{movie}",
                            arguments = listOf(
                                navArgument("movie") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            it.arguments?.getString("movie")?.let { jsonString ->
                                val movie = jsonString.fromJson(Movie::class.java)
                                MovieDetailScreen(movie = movie)
                            }
                        }
                    }
                }
            }
        }
    }
}
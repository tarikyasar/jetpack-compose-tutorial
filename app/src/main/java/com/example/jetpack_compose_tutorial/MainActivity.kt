package com.example.jetpack_compose_tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.jetpack_compose_tutorial.navigation.Screen
import com.example.jetpack_compose_tutorial.ui.theme.JetpackComposeTutorialTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val navController = rememberAnimatedNavController()
            val movies = getMovies(context)

            val springSpec = spring<IntOffset>(dampingRatio = Spring.DampingRatioNoBouncy)
            val offSetX = 1000

            JetpackComposeTutorialTheme {
                Surface(
                    color = Color.White,
                    modifier = Modifier.fillMaxSize()
                ) {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screen.MovieList.route,
                        enterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { offSetX },
                                animationSpec = springSpec
                            )
                        },
                        exitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { -offSetX },
                                animationSpec = springSpec
                            )
                        },
                        popEnterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { -offSetX },
                                animationSpec = springSpec
                            )
                        },
                        popExitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { offSetX },
                                animationSpec = springSpec
                            )
                        }
                    ) {
                        composable(Screen.MovieList.route) {
                            MovieListScreen(movies = movies, navController = navController)
                        }

                        composable(
                            route = "${Screen.MovieDetail.route}/{movieName}",
                            arguments = listOf(
                                navArgument("movieName") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val movie = it.arguments?.getString("movieName")
                                ?.let { it1 -> getMovie(it1) }
                            MovieDetailScreen(movie = movie!!)
                        }
                    }
                }
            }
        }
    }
}
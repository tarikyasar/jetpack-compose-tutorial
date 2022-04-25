package com.example.jetpack_compose_tutorial.navigation

sealed class Screen(val route: String) {
    object MovieList : Screen(route = "movieList")
    object MovieDetail : Screen(route = "movieDetail")
}

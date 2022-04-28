package com.example.jetpack_compose_tutorial

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.jetpack_compose_tutorial.data.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun getJsonDataFromAsset(
    context: Context,
    fileName: String
): String? {
    val jsonString: String

    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }

    return jsonString
}

fun getMovies(context: Context): List<Movie> {
    val jsonFileString = getJsonDataFromAsset(context, "movies.json")

    val gson = Gson()

    val listMovieType = object : TypeToken<List<Movie>>() {}.type

    return gson.fromJson(jsonFileString, listMovieType)
}

@Composable
fun getMovie(movieName: String): Movie? {
    val context = LocalContext.current
    val movies = getMovies(context)

    return movies.find { it.title == movieName }
}
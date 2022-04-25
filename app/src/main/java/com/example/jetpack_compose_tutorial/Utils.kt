package com.example.jetpack_compose_tutorial

import android.content.Context
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

fun createMoviePosterLink(link: String): String {
    return "https://m.media-amazon.com/images/M/${link}"
}

fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}

fun <A> A.toJson(): String? {
    return Gson().toJson(this)
}
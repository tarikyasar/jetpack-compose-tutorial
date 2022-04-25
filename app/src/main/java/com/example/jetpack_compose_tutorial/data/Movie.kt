package com.example.jetpack_compose_tutorial.data

import java.io.Serializable

data class Movie(
    val title: String,
    val director: String,
    val year: String,
    val runtime: String,
    val plot: String,
    val rating: Double,
    val poster: String,
) : Serializable
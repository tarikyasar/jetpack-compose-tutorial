package com.example.jetpack_compose_tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpack_compose_tutorial.data.Movie
import com.example.jetpack_compose_tutorial.navigation.Screen

@Composable
fun MovieListScreen(
    movies: List<Movie>,
    navController: NavController
) {
    MovieListContent(movies = movies, navController = navController)
}


@Composable
fun MovieListContent(
    movies: List<Movie>,
    navController: NavController
) {
    LazyColumn {
        items(movies) { movie ->
            MovieListItem(
                movie = movie,
                onItemClick = {
                    navController.navigate("${Screen.MovieDetail.route}/${movie.title}")
                }
            )

            Divider()
        }
    }
}

@Composable
fun MovieListItem(
    movie: Movie,
    onItemClick: () -> Unit
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .height(120.dp)
            .clickable { onItemClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = movie.poster),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
            )

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .weight(1f)
            ) {
                Text(
                    text = movie.title,
                    fontSize = 20.sp
                )

                Text(
                    text = movie.director,
                    fontSize = 14.sp
                )
            }

            ImdbRating(
                rating = movie.rating,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 4.dp)
            )

        }
    }
}

@Composable
fun ImdbRating(rating: Double, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .padding(end = 2.dp)
        )

        Text(text = rating.toString(), color = colorResource(id = R.color.rating_color))
    }
}
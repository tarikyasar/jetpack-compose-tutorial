package com.example.jetpack_compose_tutorial

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.jetpack_compose_tutorial.data.Movie
import com.example.jetpack_compose_tutorial.ui.theme.DominantColorState
import com.example.jetpack_compose_tutorial.ui.theme.DynamicThemePrimaryColorsFromImage
import com.example.jetpack_compose_tutorial.ui.theme.rememberDominantColorState
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MovieDetailScreen(movie: Movie) {
    val dominantColorState: DominantColorState = rememberDominantColorState()
    val coroutineScope = rememberCoroutineScope()

    DynamicThemePrimaryColorsFromImage(dominantColorState) {
        coroutineScope.launch {
            dominantColorState.updateColorsFromImageUrl(createMoviePosterLink(movie.poster))
        }

        Surface(color = dominantColorState.color) {
            MovieDetailContent(movie = movie)
        }
    }
}

@Composable
fun MovieDetailContent(movie: Movie) {
    Box {
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            elevation = 8.dp,
            modifier = Modifier
                .padding(top = 280.dp)
                .padding(horizontal = 8.dp)
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = movie.title,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(end = 4.dp)
                )

                Text(
                    text = "${movie.year} - ${movie.runtime}",
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Divider()

                MovieStatistics(rating = movie.rating)

                Divider()

                MovieInformation(plot = movie.plot, director = movie.director)
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = createMoviePosterLink(movie.poster)),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(200.dp)
            )
        }
    }
}

@Composable
fun MovieInformation(plot: String, director: String) {
    Column {
        Text(
            text = "Storyline",
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(bottom = 4.dp)
        )

        Text(text = plot)

        Row(Modifier.padding(vertical = 10.dp)) {
            Text(
                text = "Director:",
                Modifier.padding(end = 4.dp),
                fontWeight = FontWeight.Medium
            )

            Text(text = director)
        }
    }
}

@Composable
fun MovieStatistics(rating: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        ImdbRating(rating = rating)

        TasteRating()

        WatchedCount()
    }
}

@Composable
fun TasteRating() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            
            Image(
                painter = painterResource(id = R.drawable.ic_chart),
                contentDescription = "",
                Modifier.size(24.dp)
            )

            Text(text = "${(80..95).random()}%")
        }

        Text(text = "In your taste", fontSize = 12.sp)
    }
}

@Composable
fun WatchedCount() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "${(5000..10000).random()}+")

        Text(text = "Watched", fontSize = 12.sp)
    }
}

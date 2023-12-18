@file:OptIn(ExperimentalLayoutApi::class)

package com.kotlin.mvvm.features.movies.movieDetail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.kotlin.mvvm.features.movies.movieDetail.model.GenreModel
import com.kotlin.mvvm.features.movies.movieDetail.model.MovieDetailResponseModel
import com.kotlin.mvvm.features.movies.movieDetail.viewModel.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    movieId: Int = 0,
    viewModel: MovieDetailViewModel,
    navController: NavHostController,
) {
    val movieData by viewModel.movieData.observeAsState(null)
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchDetailMovie(movieId)
    }


    if (movieData != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            _header(
                onBackPress = {
                    navController.navigateUp()
                },
                movieData = movieData!!,
            )
            _content(
                movieData = movieData!!,
            )
        }
    }

}

@Composable
fun _content(
    movieData: MovieDetailResponseModel,
) {
    Box(
        modifier = Modifier.padding(horizontal = 10.dp),
    ) {
        Column {
            Row {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/original${movieData.poster_path}",
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp),
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "${movieData.original_title}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${movieData.release_date}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Status: ${movieData.status}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = "Genres:", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp,
                            alignment = Alignment.Start
                        ),
                    ) {
                        movieData.genres.forEach { item: GenreModel ->
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 5.dp)
                                    .clip(CircleShape)
                                    .background(
                                        color = Color(0xFFf2f2f2), shape = CircleShape
                                    )
                            ) {
                                Text(
                                    text = item.name,
                                    fontSize = 17.sp,
                                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
                                )
                            }
                        }
                    }
                }
            }
            Text(
                text = "${movieData.overview}",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}


@Composable
fun _header(onBackPress: () -> Unit, movieData: MovieDetailResponseModel) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/original${movieData.backdrop_path}",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .padding(5.dp)
                .clip(CircleShape)
                .clickable {
                    onBackPress()
                }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(10.dp)
                    .size(25.dp)
                    .background(color = Color.Transparent),
                tint = Color.White,
            )
        }
    }
}
package com.kotlin.mvvm.features.movies.movie.presentation
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.kotlin.mvvm.features.movies.movie.model.Movies
import com.kotlin.mvvm.features.movies.movie.viewModel.MovieViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MovieScreen(
    navController: NavHostController,
    viewModel: MovieViewModel,
) {
    val movieDatas by viewModel.movieData.observeAsState(emptyList())
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { viewModel.onRefresh() })

    LaunchedEffect(Unit) {
        viewModel.fetchMovie()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFa8a8a8)),
                actions = {
                    IconButton(onClick = {
                        viewModel.onLogout()
                    }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                    }
                },
            )
        }
    ) { padding ->
        // Column with your content
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(color = Color.White)
            .pullRefresh(pullRefreshState)
        ) {
            if (movieDatas.isEmpty()) {
                loading()
            } else {
                // Display the movie data in a LazyColumn
                LazyColumn (
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(movieDatas) { item: Movies ->
                        card(
                            navController = navController,
                            item = item
                        )
                    }
                }

                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                )
            }
        }
    }
}

@Composable
fun loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .padding(bottom = 10.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Loading...",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun card(navController: NavHostController, item: Movies) {
    Log.i("Hasil", item.poster_path.toString())
    Box(modifier = Modifier
        .padding(vertical = 5.dp, horizontal = 10.dp)
        .background(color = Color(0xFFf2f2f2), shape = RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp))
        .clickable {
            navController.navigate("movie/${item.id}")
        }
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${item.poster_path.toString()}",
                contentDescription = null,
                modifier = Modifier
                    .width(70.dp)
                    .padding(end = 10.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = item.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text =
                if (!item.overview.isEmpty()) {
                    item.overview
                } else {
                    "No Description"
                }, fontSize = 15.sp,)
            }
        }
    }
}
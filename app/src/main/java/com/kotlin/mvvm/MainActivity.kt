package com.kotlin.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kotlin.mvvm.components.utils.LocalStorageUtils
import com.kotlin.mvvm.features.login.presentation.LoginScreen
import com.kotlin.mvvm.features.login.viewModel.LoginViewModel
import com.kotlin.mvvm.features.movies.movie.presentation.MovieScreen
import com.kotlin.mvvm.features.movies.movie.viewModel.MovieViewModel
import com.kotlin.mvvm.features.movies.movieDetail.presentation.MovieDetailScreen
import com.kotlin.mvvm.features.movies.movieDetail.viewModel.MovieDetailViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavigationComponent(navController = navController)
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {

    val context = LocalContext.current
    val ls = remember { LocalStorageUtils(context) }

    NavHost(
        navController = navController,
        startDestination = "start"
    ) {
        composable("start") {
            // Check the login state and navigate accordingly
            if (ls.isLoggedIn) {
                navController.navigate("movies") {
                    // Clear the back stack, so the user can't navigate back to the "start" screen after logging in
                    popUpTo(0)
                }
            } else {
                navController.navigate("login") {
                    // Clear the back stack, so the user can't navigate back to the "start" screen after logging out
                    popUpTo(0)
                }
            }
        }
        composable("login") {
            val loginVm: LoginViewModel = remember { LoginViewModel(ls, navController) }
            LoginScreen(
                navController = navController,
                viewModel = loginVm
            )
        }
        composable("movies") {
            val movieVm: MovieViewModel = remember { MovieViewModel(ls, navController) }
            // Proceed to the "movies" screen
            MovieScreen(
                navController = navController,
                viewModel = movieVm
            )
        }

        composable("movie/{id}") { backStackEntry ->
            val movieDetailVm: MovieDetailViewModel = remember { MovieDetailViewModel() }
            val movieId = backStackEntry.arguments?.getString("id")
            if (movieId != null) {
                MovieDetailScreen(
                    movieId = movieId.toInt(),
                    viewModel = movieDetailVm,
                    navController = navController,
                )
            } else {
                Text(text = "Unknown")
            }
        }
    }
}


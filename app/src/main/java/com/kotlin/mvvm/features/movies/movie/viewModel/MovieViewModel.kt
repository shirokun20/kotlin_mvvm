package com.kotlin.mvvm.features.movies.movie.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.kotlin.mvvm.components.utils.LocalStorageUtils
import com.kotlin.mvvm.features.movies.movie.model.Movies
import com.kotlin.mvvm.features.movies.movie.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val ls: LocalStorageUtils, private val navHostController: NavHostController): ViewModel() {
    private val repository = MovieRepository()
    private val _moviesData = MutableLiveData<List<Movies>>()
    val movieData: LiveData<List<Movies>> = _moviesData

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun fetchMovie() {
        viewModelScope.launch {
            try {
                val data = repository.getUpcommingMovie()
                _moviesData.value = data.results
                Log.i("FetchMovie", _moviesData.value.toString())
            } catch (e: Exception) {
                Log.e("FetchMovie", e.toString())
            } finally {
                _isRefreshing.emit(false)
            }
        }
    }

    fun onRefresh() {
        _isRefreshing.value = true
        _moviesData.value = emptyList()
        fetchMovie()
    }

    fun onLogout() {
        ls.isLoggedIn = false
        navHostController.navigate("login") {
            popUpTo(0)
        }
    }
}
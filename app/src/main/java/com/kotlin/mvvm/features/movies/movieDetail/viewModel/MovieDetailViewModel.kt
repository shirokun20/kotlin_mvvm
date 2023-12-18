package com.kotlin.mvvm.features.movies.movieDetail.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.features.movies.movieDetail.model.MovieDetailResponseModel
import com.kotlin.mvvm.features.movies.movieDetail.repository.MovieDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel: ViewModel() {
    private val repository = MovieDetailRepository()
    private val _movieData = MutableLiveData<MovieDetailResponseModel>()
    val movieData: LiveData<MovieDetailResponseModel> = _movieData

    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun fetchDetailMovie(id: Int) {
        viewModelScope.launch {
            try {
                val data = repository.getMovieDetails(id)
                _movieData.value = data
                Log.i("FetchdDetailMovie", _movieData.value.toString())
            } catch (e: Exception) {
                Log.e("FetchdDetailMovie", e.toString())
            } finally {
                _isRefreshing.emit(false)
            }
        }
    }
}
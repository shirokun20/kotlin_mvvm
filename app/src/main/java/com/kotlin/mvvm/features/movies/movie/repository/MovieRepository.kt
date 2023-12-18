package com.kotlin.mvvm.features.movies.movie.repository

import com.kotlin.mvvm.components.intances.RetrofitInstance
import com.kotlin.mvvm.features.movies.movie.model.MovieResponseModel

class MovieRepository {
    private val apiService = RetrofitInstance.apiService
    suspend fun getUpcommingMovie(): MovieResponseModel {
        return apiService.getUpcommingMovies()
    }
}
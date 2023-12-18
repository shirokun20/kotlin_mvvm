package com.kotlin.mvvm.features.movies.movieDetail.repository

import com.kotlin.mvvm.components.intances.RetrofitInstance
import com.kotlin.mvvm.features.movies.movieDetail.model.MovieDetailResponseModel

class MovieDetailRepository{
    private val apiService = RetrofitInstance.apiService
    suspend fun getMovieDetails(id: Int): MovieDetailResponseModel {
        return apiService.getMovieDetails(movie_id = id)
    }
}
package com.kotlin.mvvm.components.services

import com.kotlin.mvvm.features.movies.movieDetail.model.MovieDetailResponseModel
import com.kotlin.mvvm.features.movies.movie.model.MovieResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/upcoming?language=en-US&page=1")
    suspend fun getUpcommingMovies(): MovieResponseModel

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movie_id: Int): MovieDetailResponseModel
}
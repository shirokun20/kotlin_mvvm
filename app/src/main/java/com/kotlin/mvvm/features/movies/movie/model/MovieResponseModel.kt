package com.kotlin.mvvm.features.movies.movie.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MovieResponseModel(
    @Json(name = "dates") val dates: Dates,
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<Movies>,
    @Json(name = "total_pages") val total_pages: Int,
    @Json(name = "total_results") val total_results: Int
)

@JsonClass(generateAdapter = true)
data class Dates(
    @Json(name = "maximum") val maximum: String,
    @Json(name = "minimum") val minimum: String
)

@JsonClass(generateAdapter = true)
data class Movies(
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "backdrop_path") val backdrop_path: String?,
    @Json(name = "genre_ids") val genre_ids: List<Int>,
    @Json(name = "id") val id: Int,
    @Json(name = "original_language") val original_language: String,
    @Json(name = "original_title") val original_title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "poster_path") val poster_path: String,
    @Json(name = "release_date") val release_date: String,
    @Json(name = "title") val title: String,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val vote_average: Double,
    @Json(name = "vote_count") val vote_count: Int
)
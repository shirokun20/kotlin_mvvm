package com.kotlin.mvvm.features.movies.movieDetail.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailResponseModel(
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "backdrop_path") val backdrop_path: String,
    @Json(name = "belongs_to_collection") val belongs_to_collection: CollectionModel,
    @Json(name = "budget") val budget: Int,
    @Json(name = "genres") val genres: List<GenreModel>,
    @Json(name = "homepage") val homepage: String,
    @Json(name = "id") val id: Int,
    @Json(name = "imdb_id") val imdb_id: String,
    @Json(name = "original_language") val original_language: String,
    @Json(name = "original_title") val original_title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "poster_path") val poster_path: String,
    @Json(name = "production_companies") val production_companies: List<ProductionCompanyModel>,
    @Json(name = "production_countries") val production_countries: List<ProductionCountryModel>,
    @Json(name = "release_date") val release_date: String,
    @Json(name = "revenue") val revenue: Int,
    @Json(name = "runtime") val runtime: Int,
    @Json(name = "spoken_languages") val spoken_languages: List<SpokenLanguageModel>,
    @Json(name = "status") val status: String,
    @Json(name = "tagline") val tagline: String,
    @Json(name = "title") val title: String,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val vote_average: Double,
    @Json(name = "vote_count") val vote_count: Int
)

@JsonClass(generateAdapter = true)
data class CollectionModel(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "poster_path") val poster_path: String,
    @Json(name = "backdrop_path") val backdrop_path: String
)

@JsonClass(generateAdapter = true)
data class GenreModel(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)
@JsonClass(generateAdapter = true)
data class ProductionCompanyModel(
    @Json(name = "id") val id: Int,
    @Json(name = "logo_path") val logo_path: String,
    @Json(name = "name") val name: String,
    @Json(name = "origin_country") val origin_country: String
)

@JsonClass(generateAdapter = true)
data class ProductionCountryModel(
    @Json(name = "iso_3166_1") val iso_3166_1: String,
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class SpokenLanguageModel(
    @Json(name = "english_name") val english_name: String,
    @Json(name = "iso_639_1") val iso_639_1: String,
    @Json(name = "name") val name: String
)
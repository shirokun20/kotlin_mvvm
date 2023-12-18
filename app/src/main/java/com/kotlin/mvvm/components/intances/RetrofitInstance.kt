package com.kotlin.mvvm.components.intances

import com.kotlin.mvvm.components.services.ApiService
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhNjc0YjZmMmYyZTBmNWFiN2MzYzNiNTA2YzRkYjNmMCIsInN1YiI6IjY1N2MxMTY2ZWM4YTQzMDEzNzAxMGU2NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.F3s7ULxmddDRq4A0nr6l7RVbc8T87fJIPEWV0THc0ss"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest: Request = chain.request()

                // Add headers (e.g., Authorization header with your token)
                val newRequest: Request = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $API_KEY") // Replace with your actual auth token
                    .header("Accept", "application/json")
                    .build()

                chain.proceed(newRequest)
            }
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
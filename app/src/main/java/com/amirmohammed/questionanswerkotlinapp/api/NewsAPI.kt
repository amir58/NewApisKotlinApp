package com.amirmohammed.questionanswerkotlinapp.api

import com.amirmohammed.questionanswerkotlinapp.models.NewsResponse
import com.amirmohammed.questionanswerkotlinapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") country: String = "eg",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>

    // Call<NewsResponse> , Single<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchStatement: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>

}
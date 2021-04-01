package com.amirmohammed.questionanswerkotlinapp.ui.api

import com.amirmohammed.questionanswerkotlinapp.ui.models.NewsResponce
import com.amirmohammed.questionanswerkotlinapp.ui.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("county") country: String = "eg",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<NewsResponce>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchStatement: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<NewsResponce>

}
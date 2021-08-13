package com.amirmohammed.questionanswerkotlinapp.repository

import com.amirmohammed.questionanswerkotlinapp.api.RetrofitClient
import com.amirmohammed.questionanswerkotlinapp.db.ArticleDatabase
import com.amirmohammed.questionanswerkotlinapp.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {


    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitClient.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitClient.api.searchForNews(searchQuery, pageNumber)

    suspend fun insertArticle(article: Article) = db.getArticleDao().insertArticle(article)

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles();

}
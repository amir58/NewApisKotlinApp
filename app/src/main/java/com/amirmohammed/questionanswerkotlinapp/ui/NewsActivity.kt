package com.amirmohammed.questionanswerkotlinapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.amirmohammed.questionanswerkotlinapp.R
import com.amirmohammed.questionanswerkotlinapp.databinding.ActivityNewsBinding
import com.amirmohammed.questionanswerkotlinapp.db.ArticleDatabase
import com.amirmohammed.questionanswerkotlinapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)


        val newsResponse = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsResponse)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(NewsViewModel::class.java)

        binding.bottomNavigation.setupWithNavController(
            Navigation.findNavController(this, R.id.news_nav_host_fragment))
    }



}


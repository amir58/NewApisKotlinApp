package com.amirmohammed.questionanswerkotlinapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.amirmohammed.questionanswerkotlinapp.R
import com.amirmohammed.questionanswerkotlinapp.databinding.ActivityNewsBinding
import com.amirmohammed.questionanswerkotlinapp.ui.core.AppClass.Companion.context
import com.bumptech.glide.Glide

class NewsActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)

        binding.bottomNavigation.setupWithNavController(
            Navigation.findNavController(this,R.id.news_nav_host_fragment))

    }

    companion object{

        @BindingAdapter("glide")
        fun loadImage(imageView: ImageView, imageUrl:String) {
            Glide.with(context).load(imageUrl).into(imageView)
        }
    }

}


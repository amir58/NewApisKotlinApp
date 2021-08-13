
package com.amirmohammed.questionanswerkotlinapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.amirmohammed.questionanswerkotlinapp.core.AppClass
import com.bumptech.glide.Glide

class Constants {
    companion object {
        const val API_KEY = "fa72aea7f1af46a6a45be8aa23e21b64"
        const val BASE_URL = "https://newsapi.org/"
        const val SEARCH_NEWS_TIME_DELAY = 500L

        @JvmStatic
        @BindingAdapter("glide")
        fun loadImage(imageView: ImageView, imageUrl: String?) {
            if (imageUrl == null) return
            Glide.with(AppClass.context).load(imageUrl).into(imageView)
        }
    }

}
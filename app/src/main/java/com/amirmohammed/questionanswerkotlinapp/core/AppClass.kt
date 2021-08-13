package com.amirmohammed.questionanswerkotlinapp.core

import android.app.Application
import android.content.Context

class AppClass : Application() {

    companion object{
        lateinit var context:Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext // getApplicationContext();
    }

}
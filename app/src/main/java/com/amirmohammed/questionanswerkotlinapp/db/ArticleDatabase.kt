package com.amirmohammed.questionanswerkotlinapp.db

import android.content.Context
import androidx.room.*
import com.amirmohammed.questionanswerkotlinapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase(){

    abstract fun getArticleDao(): ArticleDao

    companion object{
        @Volatile
        private var  instance: ArticleDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(this)
        {
            instance ?: createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "articleDB"
        ).build()

        fun getInstance(context: Context) : ArticleDatabase {
                if (instance == null) {
                        instance = Room.databaseBuilder(
                            context,
                            ArticleDatabase::class.java,
                            "articleDB"
                        ).build()
                }
                return instance!!
        }

    }

}
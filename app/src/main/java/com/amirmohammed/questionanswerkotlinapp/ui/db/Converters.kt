package com.amirmohammed.questionanswerkotlinapp.ui.db

import androidx.room.TypeConverter
import com.amirmohammed.questionanswerkotlinapp.ui.models.Source


class Converters {

    @TypeConverter
    fun fromSource(source: Source): String  {
        return source.name
    }

    fun toSource(name: String): Source {
        return Source(name, name)
    }

}
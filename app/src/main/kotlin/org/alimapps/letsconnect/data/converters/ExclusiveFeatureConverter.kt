package org.alimapps.letsconnect.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ExclusiveFeatureConverter {
    @TypeConverter
    fun fromAnswerList(value: List<String>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<String>?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toAnswerList(value: String?): List<String>? {
        val gson = Gson()
        val type = object : TypeToken<List<String>?>() {}.type
        return gson.fromJson(value, type)
    }
}
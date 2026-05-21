package org.alimapps.letsconnect.core.network.model.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FeatureFlagConverter {
    @TypeConverter
    fun fromEntity(value: Any?): String {
        val gson = Gson()
        val type = object : TypeToken<Any>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toEntity(value: String): Any? {
        val gson = Gson()
        val type = object : TypeToken<Any>() {}.type
        return gson.fromJson(value, type)
    }
}
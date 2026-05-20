package org.alimapps.letsconnect.notification.local.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.alimapps.letsconnect.core.common.notification.NotificationMainExtra

class NotificationMainExtraConverter {
    @TypeConverter
    fun fromEntity(value: NotificationMainExtra): String {
        val gson = Gson()
        val type = object : TypeToken<NotificationMainExtra>() {}.type
        return gson.toJson(value, type)
    }
    
    @TypeConverter
    fun toEntity(value: String): NotificationMainExtra {
        val gson = Gson()
        val type = object : TypeToken<NotificationMainExtra>() {}.type
        return gson.fromJson(value, type)
    }
}
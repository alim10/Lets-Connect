package org.alimapps.letsconnect.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.alimapps.letsconnect.notification.domain.model.NotificationCenterType

class NotificationCenterTypeConverter {
    @TypeConverter
    fun fromEntities(value: NotificationCenterType?): String {
        val gson = Gson()
        val type = object : TypeToken<NotificationCenterType?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toEntities(value: String): NotificationCenterType? {
        val gson = Gson()
        val type = object : TypeToken<NotificationCenterType?>() {}.type
        return gson.fromJson(value, type)
    }
}
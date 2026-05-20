package org.alimapps.letsconnect.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.alimapps.letsconnect.notification.domain.model.LatestNotificationItem

class LatestNotificationConverter {
    @TypeConverter
    fun fromEntities(value: LatestNotificationItem?): String {
        val gson = Gson()
        val type = object : TypeToken<LatestNotificationItem?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toEntities(value: String): LatestNotificationItem? {
        val gson = Gson()
        val type = object : TypeToken<LatestNotificationItem?>() {}.type
        return gson.fromJson(value, type)
    }
}
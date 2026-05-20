//package org.alimapps.letsconnect.data.converters
//
//import androidx.room.TypeConverter
//import com.lean.sehhaty.common.enums.Duration
//
//class DurationConverter{
//    @TypeConverter
//    fun fromEntity(value: Duration): String {
//        return value.name
//    }
//
//    @TypeConverter
//    fun toEntity(value: String): Duration {
//        return enumValueOf<Duration>(value)
//    }
//}
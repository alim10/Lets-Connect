//package org.alimapps.letsconnect.data.converters
//
//import androidx.room.TypeConverter
//
//class CampaignStatusConverter{
//    @TypeConverter
//    fun fromEntity(value: CampaignStatus): String {
//        return value.name
//    }
//
//    @TypeConverter
//    fun toEntity(value: String): CampaignStatus {
//        return enumValueOf<CampaignStatus>(value)
//    }
//}
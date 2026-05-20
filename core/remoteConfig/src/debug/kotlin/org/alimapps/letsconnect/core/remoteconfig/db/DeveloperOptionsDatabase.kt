package org.alimapps.letsconnect.core.remoteconfig.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.alimapps.letsconnect.core.remoteconfig.dao.CurlRequestsDao
import org.alimapps.letsconnect.core.remoteconfig.dao.FeatureFlagDao
import org.alimapps.letsconnect.core.remoteconfig.dao.MockLocationDao
import org.alimapps.letsconnect.core.remoteconfig.model.CacheCurlModel
import org.alimapps.letsconnect.core.remoteconfig.model.CacheFeatureFlagModel
import org.alimapps.letsconnect.core.remoteconfig.model.CacheMockLocationModel
import org.alimapps.letsconnect.core.remoteconfig.model.converter.FeatureFlagConverter

@Database(
    exportSchema = false,
    entities = [
        CacheCurlModel::class,
        CacheMockLocationModel::class,
        CacheFeatureFlagModel::class,
    ],
    version = 101,
)
@TypeConverters(
    FeatureFlagConverter::class
)
abstract class DeveloperOptionsDatabase: RoomDatabase() {
    abstract fun curlRequestsDao(): CurlRequestsDao
    abstract fun mockLocationDao(): MockLocationDao
    abstract fun featureFlagsDao(): FeatureFlagDao

    companion object {
        const val DB_NAME: String = "developer_options_db"
    }
}
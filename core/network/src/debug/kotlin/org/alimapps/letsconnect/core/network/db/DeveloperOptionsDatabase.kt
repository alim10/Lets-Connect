package org.alimapps.letsconnect.core.network.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.alimapps.letsconnect.core.network.dao.CurlRequestsDao
import org.alimapps.letsconnect.core.network.dao.FeatureFlagDao
import org.alimapps.letsconnect.core.network.dao.MockLocationDao
import org.alimapps.letsconnect.core.network.model.CacheCurlModel
import org.alimapps.letsconnect.core.network.model.CacheFeatureFlagModel
import org.alimapps.letsconnect.core.network.model.CacheMockLocationModel
import org.alimapps.letsconnect.core.network.model.converter.FeatureFlagConverter

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
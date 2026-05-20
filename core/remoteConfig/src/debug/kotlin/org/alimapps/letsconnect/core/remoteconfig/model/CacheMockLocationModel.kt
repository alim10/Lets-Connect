package org.alimapps.letsconnect.core.remoteconfig.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tbl_mock_location")
data class CacheMockLocationModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val lat: Double,
    val lng: Double,
): Serializable
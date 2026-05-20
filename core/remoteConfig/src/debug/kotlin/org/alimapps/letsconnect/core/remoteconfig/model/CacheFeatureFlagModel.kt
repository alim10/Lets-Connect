package org.alimapps.letsconnect.core.remoteconfig.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tbl_feature_flag")
data class CacheFeatureFlagModel(
    @PrimaryKey(autoGenerate = false)
    val flagId: String,
    val name: String,
    val functionName: String,
    val isEnable: Boolean? = true,
    val data: Any? = null
): Serializable

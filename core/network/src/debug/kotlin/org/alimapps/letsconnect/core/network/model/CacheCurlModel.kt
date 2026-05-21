package org.alimapps.letsconnect.core.network.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tbl_curl_request")
data class CacheCurlModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val url: String,
    val allCurl: String
): Serializable

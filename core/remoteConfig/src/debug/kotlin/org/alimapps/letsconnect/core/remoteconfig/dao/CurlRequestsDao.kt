package org.alimapps.letsconnect.core.remoteconfig.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.core.remoteconfig.model.CacheCurlModel

@Dao
interface CurlRequestsDao {
    @Transaction
    @Query("SELECT * FROM tbl_curl_request")
    fun getCurlRequestList(): Flow<List<CacheCurlModel>>

    @Transaction
    @Query("DELETE FROM tbl_curl_request")
    fun deleteAllCurlRequest()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurl(curl: CacheCurlModel)
}
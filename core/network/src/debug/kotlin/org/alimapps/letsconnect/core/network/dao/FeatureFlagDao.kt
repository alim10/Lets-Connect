package org.alimapps.letsconnect.core.network.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.core.network.model.CacheFeatureFlagModel

@Dao
interface FeatureFlagDao {
    @Transaction
    @Query("SELECT * FROM tbl_feature_flag WHERE flagId LIKE :key")
    fun getFeatureFlags(key: String): CacheFeatureFlagModel

    @Transaction
    @Query("SELECT * FROM tbl_feature_flag")
    fun getAllFeatureFlags(): Flow<List<CacheFeatureFlagModel>>

    @Query("Select * from tbl_feature_flag where functionName || name like '%' || :search || '%' ")
    fun getSearchResult(search: String): Flow<List<CacheFeatureFlagModel>>

    @Query("Select * from tbl_feature_flag where isEnable=0")
    fun getAllDisabled(): Flow<List<CacheFeatureFlagModel>>

    @Query("Select * from tbl_feature_flag where isEnable=1")
    fun getAllEnabled(): Flow<List<CacheFeatureFlagModel>>

    @Query("Select * from tbl_feature_flag where data is null or data='' or isEnable is NULL or isEnable is null")
    fun getAllValueOnly(): Flow<List<CacheFeatureFlagModel>>

    @Transaction
    @Query("DELETE FROM tbl_feature_flag")
    fun deleteFeatureFlags()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeatureFlag(flag: CacheFeatureFlagModel)
}
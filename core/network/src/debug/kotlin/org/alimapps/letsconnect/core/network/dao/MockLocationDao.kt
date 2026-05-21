package org.alimapps.letsconnect.core.network.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.core.network.model.CacheMockLocationModel

@Dao
interface MockLocationDao {
    @Transaction
    @Query("SELECT * FROM tbl_mock_location")
    fun getMockLocation(): Flow<CacheMockLocationModel?>

    @Transaction
    @Query("DELETE FROM tbl_mock_location")
    fun deleteMockLocation()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMockLocation(location: CacheMockLocationModel)
}
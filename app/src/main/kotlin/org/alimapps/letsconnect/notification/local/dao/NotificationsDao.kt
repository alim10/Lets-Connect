package org.alimapps.letsconnect.notification.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.core.common.BaseDao
import org.alimapps.letsconnect.notification.local.model.CachedNotifications

@Dao
interface NotificationsDao: BaseDao<CachedNotifications> {
    @Transaction
    @Query("SELECT * FROM tbl_notifications")
    fun getNotificationsList(): Flow<List<CachedNotifications>>

    @Transaction
    @Query("DELETE FROM tbl_notifications")
    suspend fun clear()

    @Transaction
    @Query("DELETE FROM tbl_notifications WHERE id = :ids")
    suspend fun deleteById (ids: String)

    @Transaction
    @Query("SELECT * FROM tbl_notifications WHERE id in (:ids)")
    suspend fun getById (ids: String): CachedNotifications?

    @Transaction
    @Query("UPDATE tbl_notifications SET read = 1 WHERE id = :id")
    suspend fun isReadByIds(id: String)
}
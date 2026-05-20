package org.alimapps.letsconnect.notification.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.core.common.BaseDao
import org.alimapps.letsconnect.notification.local.model.CachedLatestNotifications

@Dao
interface LatestNotificationDao : BaseDao<CachedLatestNotifications> {
    @Query("SELECT * FROM tbl_latest_notifications LIMIT 1")
    fun getLatestNotification(): Flow<CachedLatestNotifications?>
    
    @Query("UPDATE tbl_latest_notifications SET hasPopupNotification = 0 WHERE id = :id")
    suspend fun setLatestNotificationAsShown(id: String = "1")

    @Query("UPDATE tbl_latest_notifications SET unreadCount = 0 WHERE id = :id")
    suspend fun setAllNotificationsAsRead(id: String = "1")

    @Query("DELETE FROM tbl_latest_notifications")
    fun clear()

}
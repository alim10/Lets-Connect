package org.alimapps.letsconnect.notification.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.core.common.BaseDao
import org.alimapps.letsconnect.notification.local.model.CachedPrivateNotification

@Dao
interface PrivateNotificationsDao : BaseDao<CachedPrivateNotification> {

    @Query("SELECT * FROM notifications WHERE isPrivate = :isPrivate ORDER BY createdAt DESC")
    fun getNotificationsList(isPrivate: Boolean): Flow<List<CachedPrivateNotification>>

    @Query("SELECT * FROM notifications WHERE id in (:id)")
    suspend fun getById(id: String): CachedPrivateNotification?

    @Query("UPDATE notifications SET isRead = 1 WHERE id = :id")
    suspend fun readPrivateNotification(id: String)

    @Query("UPDATE notifications SET isRead = 1")
    suspend fun readAllPrivateNotifications()

    @Query("DELETE FROM notifications WHERE id = :id")
    suspend fun deleteById(id: String)
    
    @Query("DELETE FROM notifications WHERE isPrivate = :isPrivate")
    suspend fun deleteByType(isPrivate: Boolean)
    
    @Query("DELETE FROM notifications")
    suspend fun clear()
}
package org.alimapps.letsconnect.di.notifications
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alimapps.letsconnect.notification.NotificationsCache
import org.alimapps.letsconnect.notification.NotificationsRemote
import org.alimapps.letsconnect.notification.NotificationsRemoteImpl
import org.alimapps.letsconnect.notification.RoomNotificationsCache

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {
    @Binds
    abstract fun bindNotificationsCache(roomNotificationsCache: RoomNotificationsCache): NotificationsCache

    @Binds
    abstract fun bindNotificationsRemote(notificationsRemoteImpl: NotificationsRemoteImpl): NotificationsRemote
}
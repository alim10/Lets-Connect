package org.alimapps.letsconnect.di
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alimapps.letsconnect.notification.RoomNotificationsCache
import org.alimapps.letsconnect.notification.local.source.NotificationsCache

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {
//    @Binds
//    abstract fun bindChattingCache(roomChattingCacheImpl: RoomChattingCacheImpl): ChattingCache
//
//    @Binds
//    abstract fun bindDashboardSearchCache(roomDashboardCache: RoomDashboardCache): DashboardCache
//
//    @Binds
//    abstract fun bindNotificationsCache(roomNotificationsCache: RoomNotificationsCache): NotificationsCache
}
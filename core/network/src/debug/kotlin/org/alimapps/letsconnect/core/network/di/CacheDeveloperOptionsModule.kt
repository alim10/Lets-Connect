package org.alimapps.letsconnect.core.network.di
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alimapps.letsconnect.core.network.source.IDeveloperOptionsCache
import org.alimapps.letsconnect.core.network.source.RoomDeveloperOptionsCache
import org.alimapps.letsconnect.core.network.util.IRemoteConfigInjectorDebug

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheDeveloperOptionsModule {
    @Binds
    abstract fun bindDeveloperOptionsCache(roomCurl: RoomDeveloperOptionsCache): IDeveloperOptionsCache

//    @Binds
//    abstract fun bindRemoteConfigInjectorDebug(configDebug: RemoteConfigInjectorDebug): IRemoteConfigInjectorDebug
}

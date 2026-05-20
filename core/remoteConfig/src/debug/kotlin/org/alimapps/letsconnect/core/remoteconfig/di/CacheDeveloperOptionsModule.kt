package org.alimapps.letsconnect.core.remoteconfig.di
import com.lean.sehhaty.network.util.IRemoteConfigInjectorDebug
import com.lean.sehhaty.network.util.RemoteConfigInjectorDebug
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alimapps.letsconnect.core.common.data.local.source.IDeveloperOptionsCache
import org.alimapps.letsconnect.core.common.data.local.source.RoomDeveloperOptionsCache

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheDeveloperOptionsModule {
    @Binds
    abstract fun bindDeveloperOptionsCache(roomCurl: RoomDeveloperOptionsCache): IDeveloperOptionsCache

    @Binds
    abstract fun bindRemoteConfigInjectorDebug(configDebug: RemoteConfigInjectorDebug): IRemoteConfigInjectorDebug
}

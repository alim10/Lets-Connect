package org.alimapps.letsconnect.core.remoteconfig.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alimapps.letsconnect.core.network.util.IRemoteConfigInjectorDebug
import org.alimapps.letsconnect.core.network.repository.IRemoteConfigRepository
import org.alimapps.letsconnect.core.remoteconfig.IRemoteConfigSource
import org.alimapps.letsconnect.core.remoteconfig.repository.RemoteConfigRepositoryImpl
import org.alimapps.letsconnect.core.remoteconfig.repository.RemoteConfigSourceImpl
import org.alimapps.letsconnect.core.remoteconfig.util.RemoteConfigInjectorDebug
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteConfigModule {

    @Singleton
    @Binds
    abstract fun bindRemoteConfigSourceImpl(remoteConfigRepository: RemoteConfigSourceImpl): IRemoteConfigSource

    @Binds
    abstract fun bindRemoteConfigInjectorDebug(configDebug: RemoteConfigInjectorDebug): IRemoteConfigInjectorDebug


    @Binds
    abstract fun bindRemoteConfigRepository(remoteConfigRepository: RemoteConfigRepositoryImpl): IRemoteConfigRepository
}
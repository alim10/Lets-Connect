package org.alimapps.letsconnect.core.remoteconfig.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alimapps.letsconnect.core.remoteconfig.IRemoteConfigSource
import org.alimapps.letsconnect.core.remoteconfig.repository.IRemoteConfigRepository
import org.alimapps.letsconnect.core.remoteconfig.repository.RemoteConfigRepositoryImpl
import org.alimapps.letsconnect.core.remoteconfig.repository.RemoteConfigSourceImpl
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteConfigModule {

    @Singleton
    @Binds
    abstract fun bindRemoteConfigSourceImpl(remoteConfigRepository: RemoteConfigSourceImpl): IRemoteConfigSource

    @Binds
    abstract fun bindRemoteConfigRepository(remoteConfigRepository: RemoteConfigRepositoryImpl): IRemoteConfigRepository
}
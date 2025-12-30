package org.alimapps.letsconnect.core.common.remoteconfig.di

import org.alimapps.letsconnect.core.remoteconfig.data.repository.RemoteConfigRepositoryImpl
import org.alimapps.letsconnect.core.remoteconfig.repository.IRemoteConfigRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteConfigModule {
    
    @Binds
    abstract fun bindRemoteConfigRepository(remoteConfigRepository: RemoteConfigRepositoryImpl): IRemoteConfigRepository
}
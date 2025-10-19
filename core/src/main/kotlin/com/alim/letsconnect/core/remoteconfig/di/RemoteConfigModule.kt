package com.alim.letsconnect.core.remoteconfig.di

import com.alim.letsconnect.core.remoteconfig.data.repository.RemoteConfigRepositoryImpl
import com.alim.letsconnect.core.remoteconfig.repository.IRemoteConfigRepository
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
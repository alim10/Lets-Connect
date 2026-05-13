package org.alimapps.letsconnect.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alimapps.letsconnect.firebase.INotificationMediator
import org.alimapps.letsconnect.firebase.NotificationRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun bindNotificationMediator(notificationsRepositoryImpl: NotificationRepositoryImpl): INotificationMediator
}
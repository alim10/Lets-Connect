package org.alimapps.letsconnect.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

import org.alimapps.letsconnect.fcm.INotificationMediator
import org.alimapps.letsconnect.fcm.NotificationMediatorImpl


@Module
@InstallIn(ServiceComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun bindNotificationMediator(notificationsRepositoryImpl: NotificationMediatorImpl): INotificationMediator
}
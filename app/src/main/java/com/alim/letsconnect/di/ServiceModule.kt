package com.alim.letsconnect.di

import com.alim.letsconnect.notification.INotificationsMediator
import com.alim.letsconnect.notification.NotificationsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    
    @Binds
    abstract fun bindNotificationMediator(notificationsRepositoryImpl: NotificationsRepositoryImpl): INotificationsMediator
}
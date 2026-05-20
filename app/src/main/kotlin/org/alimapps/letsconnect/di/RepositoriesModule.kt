package org.alimapps.letsconnect.di
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alimapps.letsconnect.notification.NotificationsRepository
import org.alimapps.letsconnect.notification.INotificationsRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun bindNotificationsRepository(notificationsRepository: NotificationsRepository): INotificationsRepository

}
